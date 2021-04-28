using System;

namespace OriginApi.Models
{
    public record Tasks
    {
        public Guid Id { get; set; } = new Guid();
        public string Nombre { get; set; }
        public DateTime Creacion { get; set; } = DateTime.Now;
    }
}
