using CRUD.models;
using System.Data;
using System.Data.SqlClient;
using System.Net.Mail;
using System.Numerics;

namespace CRUD.data
{
    public class ClientData
    {
        private readonly string conexion;
        public ClientData(IConfiguration configuration) {
            conexion = configuration.GetConnectionString("SQLchain")!;
        }
        private bool IsValidEmail(string email)
        {
            try
            {
                // Intentar crear un MailAddress; si no es válido, lanzará una excepción
                var mailAddress = new MailAddress(email);
                return true;  // Si no hay excepción, el correo es válido
            }
            catch (FormatException)
            {
                return false;  // Si hay una excepción, el correo no es válido
            }
        }
        public static DateTime TryParseDateTime(string dateString)
        {
            DateTime result;

            // Intenta convertir la cadena a DateTime
            if (DateTime.TryParse(dateString, out result))
            {
                // Si la conversión es exitosa, devuelve el valor convertido
                return result;
            }
            else
            {
                // Si la conversión falla, devuelve DateTime.Now
                return DateTime.Now;
            }
        }

        public async Task<List<Cliente>> listaClientes()
        {
            List<Cliente> listaClientes = new List<Cliente>();
            String EmailExtract;

            using (var con = new SqlConnection(conexion))
            {
                await con.OpenAsync();
                SqlCommand cmd = new SqlCommand("sp_ListaClientes", con);
                cmd.CommandType = CommandType.StoredProcedure;

                using (var reader = await cmd.ExecuteReaderAsync())
                {
                    while (await reader.ReadAsync())
                    {
                        EmailExtract = reader.GetString(reader.GetOrdinal("email"));
                        if (IsValidEmail(EmailExtract))
                        {
                            listaClientes.Add(new Cliente()
                            {
                                IdCliente = reader.GetInt32("id"),
                                NameCliente = reader.GetString("name"),
                                EmailCliente = EmailExtract,
                                RegistroCliente = reader.GetDateTime("register_at").ToString("yyyy-MM-dd HH:mm:ss"),
                                UserCliente = reader.GetString("username"),
                                PassCliente = reader.GetString("password")
                            });
                        }
                        else
                        {
                            throw new InvalidOperationException("El correo electrónico no es válido.");
                        }
                    }
                }
            }
            return listaClientes;
        }
        public async Task<Cliente> getCliente(int id)
        {
            Cliente Cliente = new Cliente();
            String EmailExtract;

            using (var con = new SqlConnection(conexion))
            {
                await con.OpenAsync();
                SqlCommand cmd = new SqlCommand("sp_GetClient", con);
                cmd.Parameters.AddWithValue("@id",id);
                cmd.CommandType = CommandType.StoredProcedure;

                using (var reader = await cmd.ExecuteReaderAsync())
                {
                    while (await reader.ReadAsync())
                    {
                        EmailExtract = reader.GetString(reader.GetOrdinal("email"));
                        if (IsValidEmail(EmailExtract))
                        {
                            Cliente= new Cliente()
                            {
                                IdCliente = reader.GetInt32("id"),
                                NameCliente = reader.GetString("name"),
                                EmailCliente = EmailExtract,
                                RegistroCliente = reader.GetDateTime("register_at").ToString("yyyy-MM-dd HH:mm:ss"),
                                UserCliente = reader.GetString("username"),
                                PassCliente = reader.GetString("password")
                            };
                        }
                        else
                        {
                            throw new InvalidOperationException("El correo electrónico no es válido.");
                        }
                    }
                }
            }
            return Cliente;
        }
        public async Task<bool> postCliente(Cliente cliente)
        {
            bool response = true;
            if (!IsValidEmail(cliente.EmailCliente))
            {
                throw new InvalidOperationException("El correo electrónico no es válido.");
            }
            using (var con = new SqlConnection(conexion))
            {
                SqlCommand cmd = new SqlCommand("sp_ProtClient", con);
                cmd.Parameters.AddWithValue("@name", cliente.NameCliente);
                cmd.Parameters.AddWithValue("@email", cliente.EmailCliente);
                cmd.Parameters.AddWithValue("@register_at", TryParseDateTime(cliente.RegistroCliente));
                cmd.Parameters.AddWithValue("@username", cliente.UserCliente);
                cmd.Parameters.AddWithValue("@password", cliente.PassCliente);
                cmd.CommandType = CommandType.StoredProcedure;
                try
                {
                    await con.OpenAsync();
                    response = await cmd.ExecuteNonQueryAsync() > 0 ? true: false;
                }
                catch 
                {
                    response = false;
                }
                return response;
            }
        }
        public async Task<bool> putCliente(int id,Cliente cliente)
        {
            bool response = true;
            if (!IsValidEmail(cliente.EmailCliente))
            {
                throw new InvalidOperationException("El correo electrónico no es válido.");
            }
            using (var con = new SqlConnection(conexion))
            {
                SqlCommand cmd = new SqlCommand("sp_PutClient", con);
                cmd.Parameters.AddWithValue("@id",id);
                cmd.Parameters.AddWithValue("@name", cliente.NameCliente);
                cmd.Parameters.AddWithValue("@email", cliente.EmailCliente);
                cmd.Parameters.AddWithValue("@username", cliente.UserCliente);
                cmd.Parameters.AddWithValue("@password", cliente.PassCliente);
                cmd.CommandType = CommandType.StoredProcedure;
                try
                {
                    await con.OpenAsync();
                    response = await cmd.ExecuteNonQueryAsync() > 0 ? true : false;
                }
                catch
                {
                    response = false;
                }
                return response;
            }
        }
        public async Task<bool> deleteCliente(int id)
        {
            bool response = true; // Cambiado a false por defecto
            using (var con = new SqlConnection(conexion))
            {
                using (SqlCommand cmd = new SqlCommand("sp_DeleteClient", con))
                {
                    cmd.CommandType = CommandType.StoredProcedure;
                    cmd.Parameters.AddWithValue("@id", id);

                    try
                    {
                        await con.OpenAsync(); // Abre la conexión ANTES de ejecutar el comando
                        response = await cmd.ExecuteNonQueryAsync() > 0; // Si elimina filas, devuelve true
                    }
                    catch (Exception ex)
                    {
                        Console.WriteLine($"Error al eliminar cliente: {ex.Message}");
                        response = false;
                    }
                }
            }
            return response;
        }
    }
}
