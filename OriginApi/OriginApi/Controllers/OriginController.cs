using Microsoft.AspNetCore.Mvc;
using OriginApi.Models;
using System.Collections.Generic;
using System.Threading.Tasks;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace OriginApi.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class OriginController : ControllerBase
    {

        // GET: api/<OriginController>
        [HttpGet]
        public IEnumerable<string> Get()
        {
            return new string[] { "value1", "value2" };
        }

        // GET api/<OriginController>/5
        [HttpGet("{id}")]
        public string Get(int id)
        {
            return "value";
        }

        // POST api/<OriginController>
        [HttpPost]
        public async Task<IActionResult> Post([FromBody] Tasks tasks)
        {
            /*
             1) reciebe trask data
             2) send to cammel
             3) register on database
             */

            return Ok(tasks.ToString());
        }
    }
}
