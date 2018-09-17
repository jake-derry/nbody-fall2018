
public class Body {
	private double myXVel;
	private double myXPos;
	private double myYVel;
	private double myYPos;
	private double myMass;
	private String myFilename;
	
	public Body(double x, double y, double xv, double yv,
			double mass, String filename) {
		/*
		 * constructor for a body given the position, velocity, mass
		 * and the filename of its image.
		 */
		myXVel = xv;
		myXPos = x;
		myYVel = yv;
		myYPos = y;
		myMass = mass;
		myFilename = filename;
		
	}
	
	public Body(Body b) {
		/*
		 * constructor for a body given another body
		 * the purpose of this is to copy another body
		 */
		myXVel = b.myXVel;
		myXPos = b.myXPos;
		myYVel = b.myYVel;
		myYPos = b.myYPos;
		myMass = b.myMass;
		myFilename = b.myFilename;
	}
	
	public double getX() {
		/*
		 * a getter,
		 * gets myXPos
		 */
		return myXPos;
	}
	
	public double getY() {
		/*
		 * a getter,
		 * gets myYPos
		 */
		return myYPos;
	}
	
	public double getXVel() {
		/*
		 * a getter,
		 * gets myXVel
		 */
		return myXVel;
	}
	
	public double getYVel() {
		/*
		 * a getter,
		 * gets myYVel
		 */
		return myYVel;
	}
	
	public double getMass() {
		/*
		 * a getter,
		 * gets myMass
		 */
		return myMass;
	}
	
	public String getName() {
		/*
		 * a getter,
		 * gets myFilename
		 */
		return myFilename;
	}
	public double calcDistance(Body b) {
		/*
		 * calculates the distance between bodies b and the body 
		 * this method is called on
		 * 
		 */
		double distanceSquared = Math.pow(myXPos - b.myXPos, 2) 
				+ Math.pow(myYPos - b.myYPos, 2);
		return Math.sqrt(distanceSquared);
	}
	
	public double calcForceExertedBy(Body b) {
		/*
		 * calculates the force exerted by a single
		 * body on the body this method is called on
		 */
		double G = 6.67 * Math.pow(10, -11);
		double massesMultiplied = myMass * b.myMass;
		return G * massesMultiplied / Math.pow(calcDistance(b),2);
	}
	
	public double calcForceExertedByX(Body b) {
		/*
		 * calculates the force exerted by a body
		 * in the x direction on the body this method
		 * is called on
		 */
		double r = calcDistance(b);
		return calcForceExertedBy(b) * ((b.myXPos-myXPos)/r);
	}
	
	public double calcForceExertedByY(Body b) {
		/*
		 * calculates the force exerted by a body
		 * in the y direction on the body this method
		 * is called on
		 */
		double r = this.calcDistance(b);
		return calcForceExertedBy(b) * ((b.myYPos-myYPos)/r);
	}
	
	public double calcNetForceExertedByX(Body[] bodies) {
		/*
		 * calculates the net force exerted by each body in bodies
		 * in the x direction on the body this method is called on
		 */
		double netForce = 0.0;
		for(Body b : bodies) {
			if(! b.equals(this)) {
				netForce += calcForceExertedByX(b);
			}
		}
		return netForce;
		
		
	}
	
	public double calcNetForceExertedByY(Body[] bodies) {
		/*
		 * calculates the net force exerted by each
		 * body in bodies in the Y direction on the body this
		 * method is called on
		 */
		double netForce = 0.0;
		for(Body b : bodies) {
			if(! b.equals(this)) {
				netForce += calcForceExertedByY(b);
			}
		}
		return netForce;
		
		
	}
	
	public void draw() {
		/*
		 * places the image of the body this method is
		 * called on at (myXPos, myYPos)
		 */
		StdDraw.picture(myXPos, myYPos, "images/" + myFilename);
	}
	
	public void update(double deltaT, 
			double xForce, double yForce) {
		/*
		 * updates the variables each time it is called
		 * calculates the change by calculating the acceleration
		 * and then the velocities and then the positions
		 * finally it mutates the values
		 */
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
