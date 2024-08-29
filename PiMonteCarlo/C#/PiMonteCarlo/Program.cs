using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace PiMonteCarlo
{
    internal class Program
    {
        private const long NumPoints = 10000000000;
        private const long NumThreads = 8;

        static void Main()
        {
            Stopwatch stopwatch = Stopwatch.StartNew();

            var tasks = new Task<long>[NumThreads];
            for (long i = 0; i < NumThreads; i++)
            {
                tasks[i] = Task.Run(() => MonteCarloTask(NumPoints / NumThreads, i));
            }
            Task.WaitAll(tasks);

            long insideCircle = 0;
            foreach (var task in tasks)
            {
                insideCircle += task.Result;
            }
            double piEstimate = 4.0 * insideCircle / NumPoints;

            stopwatch.Stop();

            Console.WriteLine($"Estimated Pi: {piEstimate}, computed in {stopwatch.Elapsed.TotalSeconds} seconds");
        }

        static long MonteCarloTask(long numPoints, long seed)
        {
            // Créer une nouvelle instance de Random pour chaque tâche avec une graine différente basée sur l'index du thread
            Random random = new Random((int)seed);
            long insideCircle = 0;
            for (long i = 0; i < numPoints; i++)
            {
                double x = GenerateRandomPoint(random);
                double y = GenerateRandomPoint(random);
                if (x * x + y * y <= 1.0)
                {
                    insideCircle++;
                }
            }
            return insideCircle;
        }

        static double GenerateRandomPoint(Random random)
        {
            double randomNb = random.NextDouble();

            //scale it between -1, +1
            return (randomNb * 2) - 1;
        }
    }    
}
