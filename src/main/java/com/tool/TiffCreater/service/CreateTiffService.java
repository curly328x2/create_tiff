package com.tool.TiffCreater.service;

import java.io.IOException;

public interface CreateTiffService {

    /**
     * 指定されたフォルダ内のすべてのpdfファイルをtiffに変換
     *
     * @throws IOException
     */
    public void convertPdfToTiff() throws IOException;
}
