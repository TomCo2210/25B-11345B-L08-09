package dev.tomco.a25b_11345b_l08_09.utilities

object TimeFormatter {
    fun formatTime(lengthInMinutes: Int): String {
        val hours = lengthInMinutes / 60
        val minutes = lengthInMinutes % 60
        return buildString {
            append(String.format(null, "%02dh ", hours))
            append(String.format(null, "%02dm", minutes))
        }

    }
}