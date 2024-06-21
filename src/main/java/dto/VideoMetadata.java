package dto;

public record VideoMetadata(
    String title, String description, String duration, int totalChunks, int chunkNumber) {}
