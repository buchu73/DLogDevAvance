#include <iostream>
#include <vector>
#include <thread>
#include <random>
#include <atomic>
#include <chrono>

const long long NumPoints = 10000000000;
std::atomic<long long> insideCircle(0);  // Atomic global counter

void MonteCarloTask(long long numPoints, unsigned int seed) {
    std::mt19937 generator(seed);  // Mersenne Twister random number generator
    std::uniform_real_distribution<double> distribution(-1.0, 1.0);

    long long localInsideCircle = 0;
    for (long long i = 0; i < numPoints; ++i) {
        double x = distribution(generator);
        double y = distribution(generator);
        if (x * x + y * y <= 1.0) {
            ++localInsideCircle;
        }
    }
    insideCircle.fetch_add(localInsideCircle, std::memory_order_relaxed);  // Atomically update the global counter
}

int main() {
    int NumThreads = 12; // Get the number of available cores
    std::cout << "Using " << NumThreads << " threads.\n";

    auto start = std::chrono::high_resolution_clock::now();

    // Launch threads to perform the Monte Carlo simulation
    std::vector<std::thread> threads;
    for (int i = 0; i < NumThreads; ++i) {
        threads.emplace_back(MonteCarloTask, NumPoints / NumThreads, i);
    }

    // Wait for all threads to finish
    for (auto& thread : threads) {
        thread.join();
    }

    double piEstimate = 4.0 * insideCircle / NumPoints;

    auto end = std::chrono::high_resolution_clock::now();
    std::chrono::duration<double> elapsed = end - start;

    std::cout << "Estimated Pi: " << piEstimate << ", computed in " << elapsed.count() << " seconds" << std::endl;

    return 0;
}
