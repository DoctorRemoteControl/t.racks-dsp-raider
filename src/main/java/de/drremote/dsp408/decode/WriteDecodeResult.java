package de.drremote.dsp408.decode;

import java.util.List;

public record WriteDecodeResult(
        boolean matched,
        String commandHex,
        String parameterPath,
        String status,
        String headline,
        List<String> details
) {
    public String format() {
        StringBuilder sb = new StringBuilder();
        sb.append(matched ? "[MATCH] " : "[UNKNOWN] ");
        sb.append(commandHex).append("  ").append(headline);

        if (parameterPath != null) {
            sb.append("\n  path   : ").append(parameterPath);
        }
        if (status != null) {
            sb.append("\n  status : ").append(status);
        }
        if (details != null) {
            for (String detail : details) {
                sb.append("\n  ").append(detail);
            }
        }

        return sb.toString();
    }
}
