package com.common.tool;

import com.alibaba.fastjson.JSONObject;
import com.common.entity.HandleGoHuGo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.*;

public class HandleGoHuGoFileAppend {
    private final static CharSequence delimiter = "\r";
    private static final Logger logger = LoggerFactory.getLogger(HandleGoHuGoFileAppend.class);

    public static void main(String[] args) {
        HandleGoHuGo hugo = new HandleGoHuGo();
        String oldPath = null;
        String newPath = null;
        String hugoPath = "D:\\doc\\blog\\blob\\content\\post";

        hugo.setDraft(false);
        hugo.setTitle("我的博客");
        hugo.setDescription("测试博客");
        hugo.setAuthor("zch");
        hugo.setDate("2018-03-01T16:01:23+08:00");
        hugo.setLastmod("2018-03-01T16:01:23+08:00");

        oldPath = "E:\\studyDoc";
        newPath = "D:\\doc\\sfsdjsdj";

        List<String> extensions = new ArrayList<>(1);
        extensions.add("md");
        if (StringUtils.isBlank(oldPath) || StringUtils.isBlank(newPath)) {
            return;
        }
        try {
            copy(oldPath, newPath, hugoPath, hugo, extensions);
            System.out.println("end!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void copy(String oldPath, String newPath, String hugoPath, HandleGoHuGo huGo, List<String> extensions) throws Exception {
        File file = new File(newPath);
        FileFilter filter = new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isFile()) {
                    return extensions.contains(FilenameUtils.getExtension(f.getName()));
                } else {
                    if (".git".equals(f.getName())) {
                        return false;
                    }
                    if (".github".equals(f.getName())) {
                        return false;
                    }
                    if (".idea".equals(f.getName())) {
                        return false;
                    }
                    if (StringUtils.contains(f.getName(), ".")) {
                        return false;
                    }
                }
                return true;
            }
        };
        FileUtils.copyDirectory(new File(oldPath), file, filter, false);
        replace(file, hugoPath, huGo, file);
    }

    private static void replace(File file, String hugoPath, HandleGoHuGo base, File source) {
        if (file.isFile()) {

            try {
                String string = FileUtils.readFileToString(file, "UTF-8");
                HandleGoHuGo hugo = new HandleGoHuGo();
                if (CollectionUtils.isNotEmpty(base.getCategories())) {
                    base.getCategories().clear();
                }
                if (CollectionUtils.isNotEmpty(base.getTags())) {
                    base.getTags().clear();
                }
                BeanUtils.copyProperties(base, hugo);
                settingText(source, file, hugo);
                String headText = hugo.getvText();
                String newString = new StringJoiner(delimiter).add(headText).add(string).toString();
                FileUtils.write(file, newString, false);
                if (StringUtils.isNotBlank(hugoPath)) {
                    String path = String.format("%s%s%s", hugoPath, File.separator, file.getName());

                    FileUtils.copyFile(file, new File(path));
                }
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        } else {
            File[] files = file.listFiles();
            if (files == null || files.length == 0) {
                return;
            }
            for (File f : files) {
                replace(f, hugoPath, base, source);
            }
        }
    }

    private static void settingText(File source, File file, HandleGoHuGo hugo) {
        String sourcePath = source.getPath();
        File fileParent = null;
        if (!sourcePath.equalsIgnoreCase(file.getPath())) {
            do {
                fileParent = fileParent == null ? file.getParentFile() : fileParent;
                if (!fileParent.getName().equals(source.getName())) {
                    hugo.getTags().add(fileParent.getName());
                    hugo.getCategories().add(fileParent.getName());
                }
                fileParent = fileParent.getParentFile();
            } while (sourcePath.equalsIgnoreCase(fileParent.getPath()));
        }
        StringBuilder stringBuilder = new StringBuilder(8);
        stringBuilder.append("---").append(delimiter);
        LinkedHashMap<String, Object> map = new LinkedHashMap<>(8);
        map.put("title", hugo.getTitle());
        map.put("date", hugo.getDate());
        map.put("draft", hugo.isDraft());
        map.put("tags", JSONObject.toJSONString(hugo.getTags()));
        map.put("categories", JSONObject.toJSONString(hugo.getCategories()));
        map.put("author", hugo.getAuthor());
        map.put("description", hugo.getDescription());
        map.put("lastmod", hugo.getLastmod());
        map.forEach((s, o) -> {
            stringBuilder.append(s).append(" : ");
            if (!(o instanceof String)) {
                stringBuilder.append(o);
            } else if (StringUtils.contains(String.valueOf(o), "[")) {
                stringBuilder.append(o);
            } else {
//                stringBuilder.append('"').append(o).append('"');
                stringBuilder.append("'").append(o).append("'");
            }
            stringBuilder.append(delimiter);
        });
        stringBuilder.append("---").append(delimiter);
        hugo.setvText(stringBuilder.toString());
    }

}
