using System.ComponentModel.DataAnnotations;

namespace CRUD.models
{
    public class Cliente
    {
        public int IdCliente { get; set; }
        public String NameCliente { get; set; }
        public String EmailCliente { get; set; }
        public String RegistroCliente { get; set; }
        public String UserCliente { get; set; }
        public String PassCliente { get; set; }
    }
}
