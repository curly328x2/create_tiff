package com.tool.TiffCreater.service;

import org.springframework.stereotype.Service;

/**
 * tiffファイルを作成するサービス
 *
 * @author k_fed
 */
@Service
public class CreateTiffServiceImpl implements CreateTiffService {

    /** 対象ファイルの格納先パス */
    private static final String TARGET_DIR = "src/main/resources/targetFolder";

    /** tiff変換後のファイル格納先パス */
    private static final String RESULT_DIR = "src/main/resources/resultFolder";

    public void createTiff() {

    }
}
