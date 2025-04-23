package pac;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonUtils {

    public static List<Map.Entry<String, String>> parseSimpleJsonFile(String filePath) {
        List<Map.Entry<String, String>> list = new ArrayList<>();
        StringBuilder json = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                json.append(line.trim());
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return list;
        }
        String jsonStr = json.toString().trim();
        if (jsonStr.startsWith("{")) {
            jsonStr = jsonStr.substring(1);
        }
        if (jsonStr.endsWith("}")) {
            jsonStr = jsonStr.substring(0, jsonStr.length() - 1);
        }

        String[] pairs = jsonStr.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

        for (String pair : pairs) {
            String[] kv = pair.split(":(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
            if (kv.length == 2) {
                String key = kv[0].trim().replaceAll("^\"|\"$", "");
                String value = kv[1].trim().replaceAll("^\"|\"$", "");
                list.add(new AbstractMap.SimpleEntry<>(key, value));
            }
        }
        return list;
    }
}
