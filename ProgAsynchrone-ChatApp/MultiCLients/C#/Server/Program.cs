using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;

namespace Server
{
    internal class Program
    {
        private const int Port = 12345;
        private static List<ClientHandler> clients = new List<ClientHandler>();

        static void Main(string[] args)
        {
            var listener = new TcpListener(IPAddress.Any, Port);
            listener.Start();
            Console.WriteLine("Chat server started on port " + Port);

            while (true)
            {
                var client = listener.AcceptTcpClient();
                var clientHandler = new ClientHandler(client);
                clients.Add(clientHandler);
                Task.Run(() => clientHandler.HandleClientMessagesAsync());
            }
        }

        
    }
}
