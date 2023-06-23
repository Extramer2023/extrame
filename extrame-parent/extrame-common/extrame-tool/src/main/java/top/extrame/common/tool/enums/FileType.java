package top.extrame.common.tool.enums;

public enum FileType {

    JPEG("JPEG", "FFD8FF"),
    JPG("JPG", "FFD8FF"),
    PNG("PNG", "89504E47"),
    GIF("GIF", "47494638"),
    TIFF("TIF", "49492A00"),
    BMP("BMP", "424D"),
    BMP_16("BMP", "424D228C010000000000"),
    BMP_24("BMP", "424D8240090000000000"),
    BMP_256("BMP", "424D8E1B030000000000"),
    DWG("DWG", "41433130"),
    PSD("PSD", "38425053"),
    RTF("RTF", "7B5C727466"),
    XML("XML", "3C3F786D6C"),
    HTML("HTML", "68746D6C3E"),
    EML("EML", "44656C69766572792D646174653A"),
    DBX("DBX", "CFAD12FEC5FD746F "),
    PST("", "2142444E"),
    OLE2("OLE2", "0xD0CF11E0A1B11AE1"),
    XLS("XLS", "D0CF11E0"),
    DOC("DOC", "D0CF11E0"),
    DOCX("DOCX", "504B0304"),
    XLSX("XLSX", "504B0304"),
    MDB("MDB", "5374616E64617264204A"),
    PDF("PDF", "25504446"),
    PWL("PWL", "E3828596"),
    WAV("WAV", "57415645"),
    AVI("AVI", "41564920"),
    RAM("RAM", "2E7261FD"),
    RM("RM", "2E524D46"),
    RMVB("RMVB", "2E524D46000000120001"),
    MPG("MPG", "000001BA"),
    MOV("MOV", "6D6F6F76"),
    MID("MID", "4D546864"),
    MP4("MP4", "00000020667479706D70"),
    MP3("MP3", "49443303000000002176"),
    FLV("FLV", "464C5601050000000900"),
    TORRENT("TORRENT", "6431303A637265617465"),
    JSP("JSP", "3C2540207061676520"),
    JAVA("JAVA", "7061636B61676520"),
    CLASS("CLASS", "CAFEBABE0000002E00"),
    JAR("JAR", "504B03040A000000"),
    MF("MF", "4D616E69666573742D56"),
    EXE("EXE", "4D5A9000030000000400"),
    ELF("ELF", "7F454C4601010100"),
    WK1("WK1", "2000604060"),
    WK3("WK3", "00001A0000100400"),
    WK4("WK4", "00001A0002100400"),
    LWP("LWP", "576F726450726F"),
    SLY("SLY", "53520100");

    private final String suffix;

    private final String magicNumber;

    FileType(String suffix, String magicNumber) {
        this.suffix = suffix;
        this.magicNumber = magicNumber;
    }

    public String getSuffix() {
        return this.suffix;
    }

    public String getMagicNumber() {
        return this.magicNumber;
    }

    public static FileType getBySuffix(String suffix) {
        for (FileType fileType : FileType.values()) {
            if (fileType.getSuffix().equals(suffix.toUpperCase())) {
                return fileType;
            }
        }
        return null;
    }
}
