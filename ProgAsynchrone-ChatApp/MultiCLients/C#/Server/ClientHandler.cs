using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;

namespace Server
{
    internal class ClientHandler
    {
        private readonly TcpClient _client;

        public ClientHandler(TcpClient client)
        {
            _client = client;
        }

        public async Task HandleClientMessagesAsync()
        {
            using (var stream = _client.GetStream())
            {
                using (var reader = new StreamReader(stream))
                {
                    StreamWriter writer = new StreamWriter(stream) { AutoFlush = true };

                    // Read user name
                    await writer.WriteLineAsync("Enter your name: ");
                    string userName = await reader.ReadLineAsync();
                    Console.WriteLine(userName + " has joined the chat.");

                    try
                    {
                        string message;
                        while ((message = await reader.ReadLineAsync()) != null)
                        {
                            Console.WriteLine(userName + ": " + message);
                        }
                    }
                    catch (IOException)
                    {
                        // Handle disconnection
                    }
                    finally
                    {
                        Console.WriteLine($"{userName} has left the chat.");
                    }
                }
            };
        }

    }
}
