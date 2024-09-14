package com.tool.TiffCreater.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * tiffファイルを作成するサービス
 *
 * @author k_fed
 */
@Service
public class CreateTiffServiceImpl implements CreateTiffService {

    /** 対象ファイルの格納先パス */
    private static final String TARGET_DIR = "src/main/resources/input";

    /** tiff変換後のファイル格納先パス */
    private static final String RESULT_DIR = "src/main/resources/output";

    /**
     * {@inheritDoc}
     */
    public void convertPdfToTiff() throws IOException {

        // フォルダ内のファイルを読み込む
        List<String> readFiles = readAllFiles();
        readFiles.forEach(this::convertSingleToTiff);
    }

    /**
     * pdfファイルをtiffに変換
     *
     * @param pdfFilePath 変換するpdfファイルのパス
     */
    private void convertSingleToTiff(String pdfFilePath) {
        try (PDDocument document = PDDocument.load(new File(pdfFilePath))) {
            PDFRenderer renderer = new PDFRenderer(document);
            int pageCount = document.getNumberOfPages();

            for (int page = 0; page < pageCount; page++) {
                BufferedImage image = renderer.renderImageWithDPI(page, 300);
                String tiffFilePath = generateTiffFilePath(pdfFilePath, page);
                ImageIO.write(image, "tiff", new File(tiffFilePath));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * pdfファイルのパスとページ番号に基づいて、tiffファイルのパスを生成
     *
     * @param pdfFilePath 変換するpdfファイルのパス
     * @param pageIndex pdfファイル内のページ番号
     * @return 生成されたtiffファイルのパス
     */
    private String generateTiffFilePath(String pdfFilePath, int pageIndex) {
        Path pdfPath = Paths.get(pdfFilePath);
        String fileName = pdfPath.getFileName().toString().replace(".pdf", "_page" + (pageIndex + 1) + ".tiff");
        return Paths.get(RESULT_DIR, fileName).toString();
    }

    /**
     * フォルダ内のすべてのpdfファイルを読み込む
     *
     * @return 読み込まれたすべてのファイル
     */
    private List<String> readAllFiles() throws IOException {
        return Files.walk(Paths.get(TARGET_DIR))
                .filter(Files::isRegularFile)
                .map(Path::toString)
                .filter(string -> string.endsWith(".pdf"))
                .collect(Collectors.toList());
    }
}
