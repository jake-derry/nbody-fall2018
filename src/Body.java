
public class Body {
	private double myXVel;
	private double myXPos;
	private double myYVel;
	private double myYPos;
	private double myMass;
	private String myFilename;
	
	public Body(double x, double y, double xv, double yv,
			double mass, String filename) {
		myXVel = xv;
		myXPos = x;
		myYVel = yv;
		myYPos = y;
		myMass = mass;
		myFilename = filename;
		
	}
	
	public Body(Body b) {
		myXVel = b.myXVel;
		myXPos = b.myXPos;
		myYVel = b.myYVel;
		myYPos = b.myYPos;
		myMass = b.myMass;
		myFilename = b.myFilename;
	}
	
	public double getX() {
		return myXPos;
	}
	
	public double getY() {
		return myYPos;
	}
	
	public double getXVel() {
		return myXVel;
	}
	
	public double getYVel() {
		return myYVel;
	}
	
	public double getMass() {
		return myMass;
	}
	
	public String getName() {
		return myFilename;
	}
	
	public double calcDistance(Body b) {
		double distanceSquared = Math.pow(myXPos - b.myXPos, 2) 
				+ Math.pow(myYPos - b.myYPos, 2);
		return Math.sqrt(distanceSquared);
	}
	
	public double calcForceExertedBy(Body b) {
		double G = 6.67 * Math.pow(10, -11);
		double massesMultiplied = myMass * b.myMass;
		return G * massesMultiplied / Math.pow(calcDistance(b),2);
	}
	
	public double calcForceExertedByX(Body b) {
		double r = calcDistance(b);
		return calcForceExertedBy(b) * ((b.myXPos-myXPos)/r);
	}
	
	public double calcForceExertedByY(Body b) {
		double r = this.calcDistance(b);
		return calcForceExertedBy(b) * ((b.myYPos-myYPos)/r);
	}
	
	public double calcNetForceExertedByX(Body[] bodies) {
		double netForce = 0.0;
		for(Body b : bodies) {
			if(! b.equals(this)) {
				netForce += calcForceExertedByX(b);
			}
		}
		return netForce;
		
		
	}
	
	public double calcNetForceExertedByY(Body[] bodies) {
		double netForce = 0.0;
		for(Body b : bodies) {
			if(! b.equals(this)) {
				netForce += calcForceExertedByY(b);
			}
		}
		return netForce;
		
		
	}
	
	public void draw() {
		StdDraw.picture(myXPos, myYPos, "images/" + myFilename);
	}
	
	public void update(double deltaT, 
			double xForce, double yForce) {
		double ax = xForce / myMass;
		double ay = yForce / myMass;
		double nvx = myXVel + deltaT * ax;
		double nvy = myYVel + deltaT * ay;
		double nx = myXPos + deltaT * nvx;
		double ny = myYPos + deltaT * nvy;
		myXPos = nx;
		myYPos = ny;
		myXVel = nvx;
		myYVel = nvy;
	}
}
