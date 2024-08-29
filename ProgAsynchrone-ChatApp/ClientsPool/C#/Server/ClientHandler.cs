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
        private StreamWriter _writer;

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
                    _writer = new StreamWriter(stream) { AutoFlush = true };

                    // Read user name
                    await _writer.WriteLineAsync("Enter your name: ");
                    string userName = await reader.ReadLineAsync();

                    string message = userName + " has joined the chat.";
                    Console.WriteLine(message);
                    this.Broadcast(message);

                    try
                    {
                        string receivedMessage;
                        while ((receivedMessage = await reader.ReadLineAsync()) != null)
                        {
                            message = userName + ": " + receivedMessage;
                            Console.WriteLine(message);
                            Broadcast(message);
                        }
                    }
                    catch (IOException)
                    {
                        // Handle disconnection
                    }
                    finally
                    {
                        Console.WriteLine($"{userName} has left the chat.");
                        Broadcast($"{userName} has left the chat.");
                    }
                }
            };
        }

        private void Broadcast(string message)
        {
            Program.Broadcast(message, this);
        }

        public void SendMessage(string message)
        {
            if (_writer != null)
            {
                _writer.WriteLine(message);
            }
        }
    }
}
