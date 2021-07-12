package gb.cloud.server.service.impl.command;

import gb.cloud.server.Main;
import gb.cloud.server.service.CommandService;
import gb.cloud.server.service.impl.utils.ServerProperties;

import java.io.File;
import java.util.Objects;

public class ViewFilesInDirCommand implements CommandService {

   public String processCommand(String command) {
        final int requirementCountCommandParts = 3;

        String[] actualCommandParts = command.split("=", requirementCountCommandParts);
        if (actualCommandParts.length != requirementCountCommandParts) {
            throw new IllegalArgumentException("Command \"" + getCommand() + "\" is not correct");
        }

        ServerProperties.loggedName = actualCommandParts[2];
        return process(actualCommandParts[1]);
    }

    private static void addTarget(StringBuilder builder, String dir) {
        if (dir.startsWith(ServerProperties.localDir)) {
            builder.append("LOCAL").append(System.lineSeparator());
        }
        if (dir.startsWith(ServerProperties.cloudDir)) {
            builder.append("CLOUD").append(System.lineSeparator());
        }
    }

    private static void addLevelUp(StringBuilder builder, String dir, String startDir,  int len) {
        if (dir.startsWith(startDir) & dir.length() > len) {
            builder.append(ServerProperties.levelUp).append(System.lineSeparator());
        }
    }

    private static void showAllDirs(File dir, StringBuilder builder, String logName) {
        for (File childFile : Objects.requireNonNull(dir.listFiles())) {
            String typeFile = getTypeFile(childFile);
            if (childFile.getName().startsWith(ServerProperties.privateDir)) {
                String privateName = childFile.getName().substring(Integer.parseInt(ServerProperties.privateLen));

                if (logName.equals(privateName)) {

                    Main.log.info("Show private folder " + childFile.getName());
                    builder.append(childFile.getName()).append(" | ").append(typeFile).append(System.lineSeparator());
                }
            } else {
                builder.append(childFile.getName()).append(" | ").append(typeFile).append(System.lineSeparator());
            }

        }
    }

    private static String process(String dirPath) {
        File directory = new File(dirPath);

        if (!directory.exists()) {
            return "Directory is not exists";
        }

        StringBuilder builder = new StringBuilder();

        addTarget(builder, dirPath);
        addLevelUp(builder, dirPath, ServerProperties.cloudDir, Integer.parseInt(ServerProperties.cloudLen));
        addLevelUp(builder, dirPath, ServerProperties.localDir, Integer.parseInt(ServerProperties.localLen));

        showAllDirs(directory, builder, ServerProperties.loggedName);

        return builder.toString();
    }

    private static String getTypeFile(File childFile) {
        return childFile.isDirectory() ? "DIR" : "FILE";
    }

    public String getCommand() {
        return "ls";
    }

}
