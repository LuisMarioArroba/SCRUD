using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using CRUD.data;
using CRUD.models;

namespace CRUD.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ClienteController : ControllerBase
    {
        private readonly ClientData _clientedata;
        public ClienteController(ClientData clientedata)
        {
            this._clientedata = clientedata;
        }
        [HttpGet]
        public async Task<IActionResult> list()
        {
            List<Cliente> lista =  await _clientedata.listaClientes();
            return StatusCode(StatusCodes.Status200OK, lista);
        }

        [HttpGet("{id}")]
        public async Task<IActionResult> getFromList(int id)
        {
            Cliente lista = await _clientedata.getCliente(id);
            return StatusCode(StatusCodes.Status200OK, lista);
        }

        [HttpPost]
        public async Task<IActionResult> postClient([FromBody]Cliente _cliente)
        {
            bool response = await _clientedata.postCliente(_cliente);
            return StatusCode(StatusCodes.Status200OK, new {isSuccess = response});
        }
        [HttpPut("{id}")]
        public async Task<IActionResult> putClient(int id,[FromBody] Cliente _cliente)
        {
            bool response = await _clientedata.putCliente(id,_cliente);
            return StatusCode(StatusCodes.Status200OK, new { isSuccess = response });
        }

        [HttpDelete("{id}")]
        public async Task<IActionResult> deleteFromList(int id)
        {
            bool response = await _clientedata.deleteCliente(id);
            return StatusCode(StatusCodes.Status200OK, new { isSuccess = response });
        }
    }
}
