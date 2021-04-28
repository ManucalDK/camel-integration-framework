using Confluent.Kafka;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace OriginApi.Services
{
    public class KafkaGenericService
    {
        private readonly IProducer<string, string> _producer;
        public KafkaGenericService(string brokerList)
        {
            var _brokerList = brokerList;
            _producer = new ProducerBuilder<string, string>(ConstructConfig(_brokerList)).Build();
        }

        public async Task Produce(string topic, string message)
        {
            await _producer.ProduceAsync(topic, new Message<string, string>()
            {
                Key = Guid.NewGuid().ToString(),
                Value = message
            });
        }

        private static Dictionary<string, string> ConstructConfig(string brokerList) =>
              new Dictionary<string, string>
              {
                { "bootstrap.servers", brokerList }
              };
    }
}
