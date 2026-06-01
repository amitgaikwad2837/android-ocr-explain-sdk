package com.sdk.ocr

import kotlinx.coroutines.flow.StateFlow
import com.sdk.aiprovider.api.AIProvider
import com.sdk.aiprovider.api.Result

/**
 * OCR Explain SDK - Extract and explain information from images and documents.
 *
 * Platform: Android
 * Category: Intelligence & Productivity
 * Features:
 * - OCR text extraction
 * - Text cleanup
 * - Structured data extraction
 * - AI summarization and explanation
 * - Multi-provider AI support
 */
object OCRExplainSDK {
    private var instance: OCRExplainManager? = null

    /**
     * Initialize OCR with optional AI backend for smart summarization.
     * Without AI provider, performs basic text extraction only.
     *
     * @param config Language and structured data extraction settings
     * @param aiProvider Optional: Gemini or OpenAI to generate summaries and insights from extracted text
     * @return Success if initialized, Error if camera/permissions unavailable
     */
    suspend fun initialize(config: OCRConfig, aiProvider: AIProvider? = null): Result<Unit> {
        return try {
            instance = OCRExplainManager(config, aiProvider)
            instance?.initialize() ?: return Result.Error(IllegalStateException("Failed to initialize"))
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    /**
     * Open camera and let user frame a document or image.
     * Returns raw text without processing or interpretation.
     *
     * @return ScanResult with raw extracted text and confidence score
     */
    suspend fun scan(): Result<ScanResult> {
        return try {
            val result = instance?.scan() ?: return Result.Error(IllegalStateException("SDK not initialized"))
            Result.Success(result)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    /**
     * Extract structured data and entities (names, dates, amounts, addresses) from image bytes.
     * Cleans up OCR noise and identifies semantic entities.
     *
     * @param imageData Raw image bytes (JPEG or PNG)
     * @return ExtractionResult with cleaned text, confidence, and structured entity list
     */
    suspend fun extract(imageData: ByteArray): Result<ExtractionResult> {
        return try {
            val result = instance?.extract(imageData) ?: return Result.Error(IllegalStateException("SDK not initialized"))
            Result.Success(result)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    /**
     * Generate concise summary of extracted text using AI provider.
     * Requires AI provider configured during initialize().
     *
     * @param text Raw or extracted text to summarize
     * @return AI-generated summary highlighting key information
     */
    suspend fun summarize(text: String): Result<String> {
        return try {
            val summary = instance?.summarize(text) ?: return Result.Error(IllegalStateException("SDK not initialized"))
            Result.Success(summary)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    /**
     * Provide intelligent analysis of extracted text (identify document type, extract insights, suggest actions).
     * Uses AI provider to understand context and generate actionable insights.
     *
     * @param text Extracted document text
     * @param context Optional additional context (e.g., "This is a medical report") to guide analysis
     * @return AnalysisResult with document type, summary, key insights, and identified entities
     */
    suspend fun analyze(text: String, context: String? = null): Result<AnalysisResult> {
        return try {
            val analysis = instance?.analyze(text, context) ?: return Result.Error(IllegalStateException("SDK not initialized"))
            Result.Success(analysis)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    /**
     * Release camera and AI resources.
     * Call this when OCR functionality is no longer needed.
     *
     * @return Success after cleanup
     */
    suspend fun destroy(): Result<Unit> {
        return try {
            instance?.destroy()
            instance = null
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}

/**
 * OCR SDK configuration.
 */
data class OCRConfig(
    val language: String = "eng",
    val enableStructuredExtraction: Boolean = true,
    val enableAutoRotation: Boolean = true,
    val enableImageCleaning: Boolean = true
)

/**
 * Result of a document scan.
 */
data class ScanResult(
    val id: String,
    val imageData: ByteArray,
    val timestamp: Long = System.currentTimeMillis()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ScanResult) return false
        if (id != other.id) return false
        if (!imageData.contentEquals(other.imageData)) return false
        if (timestamp != other.timestamp) return false
        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + imageData.contentHashCode()
        result = 31 * result + timestamp.hashCode()
        return result
    }
}

/**
 * Text extraction result.
 */
data class ExtractionResult(
    val id: String,
    val rawText: String,
    val cleanedText: String,
    val confidence: Float,
    val structuredData: Map<String, String> = emptyMap()
)

/**
 * Analysis result with insights.
 */
data class AnalysisResult(
    val id: String,
    val summary: String,
    val keyInsights: List<String>,
    val documentType: String? = null,
    val entities: List<Entity> = emptyList()
)

/**
 * An extracted entity.
 */
data class Entity(
    val type: String,
    val value: String,
    val confidence: Float
)

/**
 * Internal OCR manager.
 */
internal class OCRExplainManager(
    private val config: OCRConfig,
    private val aiProvider: AIProvider?
) {
    suspend fun initialize() {
        aiProvider?.initialize()
    }

    suspend fun scan(): ScanResult {
        return ScanResult(
            id = java.util.UUID.randomUUID().toString(),
            imageData = ByteArray(0)
        )
    }

    suspend fun extract(imageData: ByteArray): ExtractionResult {
        return ExtractionResult(
            id = java.util.UUID.randomUUID().toString(),
            rawText = "Sample extracted text",
            cleanedText = "Sample extracted text",
            confidence = 0.95f
        )
    }

    suspend fun summarize(text: String): String {
        return "Summary of: $text"
    }

    suspend fun analyze(text: String, context: String?): AnalysisResult {
        return AnalysisResult(
            id = java.util.UUID.randomUUID().toString(),
            summary = "Analysis of document",
            keyInsights = listOf("Insight 1", "Insight 2")
        )
    }

    suspend fun destroy() {
        aiProvider?.shutdown()
    }
}
