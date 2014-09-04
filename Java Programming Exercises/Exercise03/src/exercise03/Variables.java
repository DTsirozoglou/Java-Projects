package exercise03;

/**Variables is a program that creates seven variables, one for each of the primitive 
number types in Java, and initialises each variable with any appropriate value.
The values can be modified!
 */
public class Variables {

	private int inte;
	private long lInt;
	private short sInt;
	private double doub;
	private float fl;
	private char ch;
	private boolean bool;
	
	public final double FD = 2.7182818284590452354;
	public final int FI = 32;	
	public final long FL = 328765392;
	public final short FS = 10000;
	public final char FC = '2';
	public final boolean FB = false;
	public final float FF = 328978;
	
	public Variables()
	 {
		 
		 inte = 10;
		 lInt = 99999;
		 sInt= 10 ;
		 doub=Math.pow(10,38);
		 fl= 4645;
		 ch= 'g';
		 bool= true ;				 
		 
	 }

	/**
	 * @return the inte
	 */
	public int getInte() {
		return inte;
	}

	/**
	 * @param inte the inte to set
	 */
	public void setInte(int inte) {
		this.inte = inte;
		// if we use this : this.FI = inte;
		// to change the value of a final constant we get an error!
	}

	/**
	 * @return the lInt
	 */
	public long getlInt() {
		return lInt;
	}

	/**
	 * @param lInt the lInt to set
	 */
	public void setlInt(long lInt) {
		this.lInt = lInt;
	}

	/**
	 * @return the sInt
	 */
	public short getsInt() {
		return sInt;
	}

	/**
	 * @param sInt the sInt to set
	 */
	public void setsInt(short sInt) {
		this.sInt = sInt;
	}

	/**
	 * @return the doub
	 */
	public double getDoub() {
		return doub;
	}

	/**
	 * @param doub the doub to set
	 */
	public void setDoub(double doub) {
		this.doub = doub;
	}

	/**
	 * @return the fl
	 */
	public float getFl() {
		return fl;
	}

	/**
	 * @param fl the fl to set
	 */
	public void setFl(float fl) {
		this.fl = fl;
	}

	/**
	 * @return the ch
	 */
	public char getCh() {
		return ch;
	}

	/**
	 * @param ch the ch to set
	 */
	public void setCh(char ch) {
		this.ch = ch;
	}

	/**
	 * @return the bool
	 */
	public boolean isBool() {
		return bool;
	}

	/**
	 * @param bool the bool to set
	 */
	public void setBool(boolean bool) {
		this.bool = bool;
	}

}
