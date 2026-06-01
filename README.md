# Android OCR Explain SDK

## Overview

OCR extraction and AI-powered explanation for documents and images

## Installation

Add the Maven dependency once artifacts are published:

~~~kotlin
dependencies {
  implementation("io.github.amitgaikwad2837:android-ocr-explain-sdk:1.0.0")
}
~~~

## Integration Example

~~~kotlin
import com.sdk.ocr.OCRExplainSDK

class ExampleUsage {
    fun setup() {
        val sdk = OCRExplainSDK()
        // Configure and call SDK APIs here based on your app flow.
    }
}
~~~

## Feature Highlights

- ocr-extraction
- text-cleanup
- structured-data-extraction
- ai-summarization
- ai-explanation
- multi-language-support

## Android Permissions

- CAMERA
- READ_EXTERNAL_STORAGE
- INTERNET

## Development

~~~bash
./gradlew build
./gradlew test
~~~

## License

MIT
