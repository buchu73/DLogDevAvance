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
        static void Main(string[] args)
        {
            var listener = new TcpListener(IPAddress.Any, Port);
            listener.Start();
            Console.WriteLine("Chat server started on port " + Port);

            while (true)
            {
                var client = listener.AcceptTcpClient();
                Task.Run(() => HandleClientMessagesAsync(client));
            }
        }

        private static async Task HandleClientMessagesAsync(TcpClient client)
        {
            using (var stream = client.GetStream())
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
