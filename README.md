> Mirror Policy: This repository is an automated mirror of the monorepo https://github.com/amitgaikwad2837/SDK.
>
> Do not push changes directly here. All changes must be made in the SDK monorepo and synced by workflow.
> Pull requests opened in this repo are for review visibility only and may be overwritten by the next sync.
# Android OCR Explain SDK

## 📦 Registry & Repository

- **Maven Central**: [io.github.amitgaikwad2837:android-ocr-explain-sdk](https://central.sonatype.com/artifact/io.github.amitgaikwad2837/android-ocr-explain-sdk)
- **GitHub**: [amitgaikwad2837/android-ocr-explain-sdk](https://github.com/amitgaikwad2837/android-ocr-explain-sdk)

## Overview

Extract and explain text from documents and images using OCR and AI-powered analysis. Performs real-time text extraction from camera feeds or images, then provides contextual explanations for accessibility and comprehension.

## Installation

Add the Maven dependency:

~~~kotlin
dependencies {
  implementation("io.github.amitgaikwad2837:android-ocr-explain-sdk:0.0.9")
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

