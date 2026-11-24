// Leetcode 937

class Solution {
    public String[] reorderLogFiles(String[] logs) {
        int letCount = 0;
        int n = logs.length;
        String[] letID = new String[n];
        String[] letContent = new String[n];
        String[] out = new String[n];

        for (int i = 0; i < n; i++) {
            // c = last character of String
            char c = logs[i].charAt(logs[i].length() - 1);

            if (c > '9')
                letCount++;
        }

        // Sorting letLogs before digLogs
        int letPos = 0, digPos = letCount;
        for (int i = 0; i < n; i++){
            char c = logs[i].charAt(logs[i].length() - 1);

            if (c > '9'){
                out[letPos++] = logs[i];
            }else{
                out[digPos++] = logs[i];
            }
        }

        // identifier and content for each letLog
        for (int i = 0; i < letPos; i++){
            String[] words = out[i].split(" ");
            letID[i] = words[0];
            String content = "";

            for (int j = 1; j < words.length; j++)
                content += words[j] + " ";

            letContent[i] = content;
        }

        // Sort letLogs (+ ID and content)
        String temp = "";
        for (int i = 0; i < letPos - 1; i++){
            for (int j = i + 1; j < letPos; j++){
                int comp = letContent[i].compareTo(letContent[j]);
                if (comp > 0 || (comp == 0 && letID[i].compareTo(letID[j]) > 0)){
                    temp = letContent[i];
                    letContent[i] = letContent[j];
                    letContent[j] = temp;

                    temp = letID[i];
                    letID[i] = letID[j];
                    letID[j] = temp;

                    temp = out[i];
                    out[i] = out[j];
                    out[j] = temp;
                }
            }
        }

        return out;
    }
}