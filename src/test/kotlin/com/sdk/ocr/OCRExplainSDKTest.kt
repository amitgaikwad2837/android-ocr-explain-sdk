package com.sdk.ocr

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class OCRExplainSDKTest {
    @Test
    fun testOCRConfig() {
        val config = OCRConfig(
            language = "eng",
            enableStructuredExtraction = true
        )
        
        assertEquals("eng", config.language)
        assertEquals(true, config.enableStructuredExtraction)
    }

    @Test
    fun testExtractionResult() {
        val result = ExtractionResult(
            id = "extract-123",
            rawText = "Hello World",
            cleanedText = "Hello World",
            confidence = 0.99f
        )
        
        assertEquals("extract-123", result.id)
        assertEquals("Hello World", result.rawText)
        assertEquals(0.99f, result.confidence)
    }

    @Test
    fun testEntity() {
        val entity = Entity(
            type = "PERSON",
            value = "John Doe",
            confidence = 0.95f
        
        assertEquals("PERSON", entity.type)
        assertEquals("John Doe", entity.value)
    }

    @Test
    fun testOCRConfigDefaults() {
        val config = OCRConfig()
        assertEquals("eng", config.language)
        assertEquals(true, config.enableStructuredExtraction)
        assertEquals(true, config.enableAutoRotation)
        assertEquals(true, config.enableImageCleaning)
    }

    @Test
    fun testExtractionResultWithStructured() {
        val structuredData = mapOf(
            "document_type" to "invoice",
            "total_amount" to "100.00"
        )
        
        val result = ExtractionResult(
            id = "extract-456",
            rawText = "Invoice Total: 100.00",
            cleanedText = "Invoice Total: 100.00",
            confidence = 0.98f,
            structuredData = structuredData
        )
        
        assertEquals(2, result.structuredData.size)
        assertEquals("invoice", result.structuredData["document_type"])
    }

    @Test
    fun testAnalysisResultWithEntities() {
        val entities = listOf(
            Entity("AMOUNT", "100.00", 0.99f),
            Entity("DATE", "2024-01-15", 0.95f),
            Entity("RECIPIENT", "John Doe", 0.92f)
        )
        
        val analysis = AnalysisResult(
            id = "analysis-123",
            summary = "This is an invoice",
            keyInsights = listOf("Total: 100.00", "Date: 2024-01-15"),
            documentType = "INVOICE",
            entities = entities
        )
        
        assertEquals(3, analysis.entities.size)
        assertEquals("INVOICE", analysis.documentType)
    }

    @Test
    fun testScanResultImageData() {
        val imageData = byteArrayOf(1, 2, 3, 4, 5)
        val result = ScanResult(
            id = "scan-789",
            imageData = imageData
        )
        
        assertEquals(5, result.imageData.size)
        assertTrue(result.timestamp > 0)
    }

    @Test
    fun testMultipleEntityTypes() {
        val entities = listOf(
            Entity("PERSON", "Alice", 0.95f),
            Entity("LOCATION", "New York", 0.90f),
            Entity("DATE", "2024-05-31", 0.98f)
        )
        
        val personEntities = entities.filter { it.type == "PERSON" }
        assertEquals(1, personEntities.size)
    }
}
