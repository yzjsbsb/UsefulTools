package cn.yzjsbsb.utils;
/**
 * 目录分离的工具类:
 */
public class UploadUtils {

	public static String getPath(String uuidFileName){
		/**
		 * 1.获得文件的唯一文件名:
		 * 2.通过唯一文件名.获得其的hashCode值.
		 * 3.hashCode & 0xf; 得到值 作为一级目录.
		 * 4.hashCode值右移4位 & 0xf;得到值 作为二级目录.
		 * 5.依次执行该操作.
		 */
		// 获得唯一文件名的hashCode:
		int code1 = uuidFileName.hashCode();
		// & 0xf;
		int d1 = code1 & 0xf;// 作为一级目录.
		// code1 右移>> 4位.
		int code2 = code1 >>> 4;
		// code2 & 0xf;
		int d2 = code2 & 0xf;// 作为二级目录.
		return "/"+d1+"/"+d2;
	}
}
