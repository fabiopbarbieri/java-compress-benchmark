# Java Compression Libraries Benchmark

This repository contains a Maven project for benchmarking three Java compression libraries: BZip2, Zstandard (Zstd), and LZMA. The benchmark analyzes file compression performance across three main types of files: images, videos, and documents.

## Overview

- **BZip2**: BZip2 is a free and open-source compression algorithm known for its efficiency in compressing various types of files.
  
- **Zstandard (Zstd)**: Zstd is a fast compression algorithm developed by Facebook, providing high compression ratios with exceptional speed.
  
- **LZMA**: LZMA (Lempel-Ziv-Markov chain algorithm) is a compression method favored for its high compression ratio.

## Usage

To run the benchmarking tool, follow these steps:

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/your_username/java-compression-benchmark.git
   ```

2. **Navigate to the Repository**:

   ```bash
   cd java-compression-benchmark
   ```

3. **Compile the Project**:

   ```bash
   mvn clean install
   ```
   
## Analysis

- **BenchmarkAll Class**: This class benchmarks the three compression libraries for file compression and decompression using both byte arrays and base64 encoding. It analyzes performance across images, videos, and documents.

- **BenchmarkZstd Class**: This class focuses on Zstd compression and includes additional analysis for image files through image resizing. It tests various compression levels from 0 to 22.

## Contributing

Contributions to this benchmarking tool are welcome! If you have suggestions for improvements or would like to add support for additional compression libraries, feel free to open an issue or submit a pull request.

## License

This repository is licensed under the [MIT License](LICENSE).
