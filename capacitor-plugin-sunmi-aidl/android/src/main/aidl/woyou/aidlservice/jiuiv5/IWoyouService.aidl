/**
* JIUI T1mini
* AIDL Version: 2.1
*/

package woyou.aidlservice.jiuiv5;

import woyou.aidlservice.jiuiv5.ICallback;
import woyou.aidlservice.jiuiv5.ILcdCallback;
import android.graphics.Bitmap;
import woyou.aidlservice.jiuiv5.ITax;

interface IWoyouService
{
    /**
	* @param buffer
	* @param size
	* @param filename
	* @param iapInterface
	*/
	void updateFirmware();

	/**
	* return:   0--unknown， A5--bootloader, C3--print
	*/
	int getFirmwareStatus();

	/**
	*  get WoyouService service version
	*/
	String getServiceVersion();

	/**
	 * @param callback
	 * @return
	 */
	void printerInit(in ICallback callback);

	/**
	* @param callback
	*/
	void printerSelfChecking(in ICallback callback);

	/**
	*/
	String getPrinterSerialNo();

	/**
	*/
	String getPrinterVersion();

	/**
	*/
	String getPrinterModal();

	/**
	*/
	int getPrintedLength();

	/**
	 * @param n:
	 * @param callback
	 * @return
	 */
	void lineWrap(int n, in ICallback callback);

	/**
	* @param data
	* @param callback
	*/
	void sendRAWData(in byte[] data, in ICallback callback);

	/**
	* @param alignment:	0--left , 1--center, 2--right
	* @param callback
	*/
	void setAlignment(int alignment, in ICallback callback);

	/**
	* @param typeface:
	*/
	void setFontName(String typeface, in ICallback callback);

	/**
	* @param fontsize:
	*/
	void setFontSize(float fontsize, in ICallback callback);

	/**
	* @param text:
	*/
	void printText(String text, in ICallback callback);

	/**
	* @param text:
	* @param typeface:
	* @param fontsize:
	*/
	void printTextWithFont(String text, String typeface, float fontsize, in ICallback callback);

	/**
	* @param colsTextArr
	* @param colsWidthArr
	* @param colsAlign	  (0 left, 1 center, 2 right)
	*/
	void printColumnsText(in String[] colsTextArr, in int[] colsWidthArr, in int[] colsAlign, in ICallback callback);


	/**
	* @param bitmap:
	*/
	void printBitmap(in Bitmap bitmap, in ICallback callback);

	/**
	* @param data:
	* @param symbology:
	*    0 -- UPC-A，
	*    1 -- UPC-E，
	*    2 -- JAN13(EAN13)，
	*    3 -- JAN8(EAN8)，
	*    4 -- CODE39，
	*    5 -- ITF，
	*    6 -- CODABAR，
	*    7 -- CODE93，
	*    8 -- CODE128
	* @param height: 		1~255, default 162
	* @param width: 		2~6,  default 2
	* @param textposition:	0--no print, 1--top, 2--bottom, 3--middle
	*/
	void printBarCode(String data, int symbology, int height, int width, int textposition,  in ICallback callback);

	/**
	* @param data:
	* @param modulesize:	(unit:point, 1 ~ 16 )
	* @param errorlevel:	level(0 ~ 3)，
	*                0 -- L ( 7%)，
	*                1 -- M (15%)，
	*                2 -- Q (25%)，
	*                3 -- H (30%)
	*/
	void printQRCode(String data, int modulesize, int errorlevel, in ICallback callback);

	/**
	* @param text:
	* Ver 1.7.6
	*/
	void printOriginalText(String text, in ICallback callback);

	/**
	*/
	void commitPrinterBuffer();

	/**
	*/
	void cutPaper(in ICallback callback);

	/**
	*/
	int getCutPaperTimes();

	/**
	*/
	void openDrawer(in ICallback callback);

	/**
	*/
	int getOpenDrawerTimes();

	/**
	*
	* @param clean:
	*
	*/
	void enterPrinterBuffer(in boolean clean);

	/**
	*
	* @param commit:
	*
	*/
	void exitPrinterBuffer(in boolean commit);

	void tax(in byte [] data,in ITax callback);

	//：0 normal 1 darkmode
	int getPrinterMode();

	int getPrinterBBMDistance();

	/**
	* @param colsTextArr
	* @param colsWidthArr
	* @param colsAlign	   (0 left, 1 center, 2 right)
	*/
	void printColumnsString(in String[] colsTextArr, in int[] colsWidthArr, in int[] colsAlign, in ICallback callback);

	/**
	* retrun value：1 print normal 2、update 3 error  4 no paper 5 hot  6 open cover 7 cutter error 8 cutter recover 505 no check
	**/
	int updatePrinterState();

	/*
	* @param flag 1 initalize 2 wake LCD 3 show LCD 4 clear
	*/
	void sendLCDCommand(in int flag);

	void sendLCDString(in String string, ILcdCallback callback);

	void sendLCDBitmap(in Bitmap bitmap, ILcdCallback callback);

		/**
    	*
    	* @param callback:
    	*
    	*/
    	void commitPrinterBufferWithCallback(in ICallback callback);

    	/**
    	*
    	* @param commit：
    	* @param callback:
    	*
    	*/
    	void exitPrinterBufferWithCallback(in boolean commit, in ICallback callback);

        /**
        *
        * @param string:
        * @param size:  0:invaild
        *               1:4(1-4)
        *               2:3(1-3)
        *               3:3(2-4)
        *               4:2(1-2)
        *               5:2(2-3)
        *               6:2(3-4)
        *               7:1(1)
        *               8:2(2)
        *               9:3(3)
        *               10:4(4)
        */
    	void sendLCDDoubleString(in String topText, in String bottomText, ILcdCallback callback);

        /**
        * @param bitmap: 	(max width384px，max size 1M)
        * @param type:      ：0 printBitmap 1 black-white print 2 grey print
        */
        void printBitmapCustom(in Bitmap bitmap, in int type, in ICallback callback);
}