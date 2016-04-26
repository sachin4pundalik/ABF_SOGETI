package com.sogeti.xmlbeans;

public class Month {
	
	private String name;

    private Weeks weeks;
    
    private String total;
    
    public Month(){
    	//this.weeks = new Weeks();
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public Weeks getWeeks ()
    {
        return weeks;
    }

    public void setWeeks (Weeks weeks)
    {
        this.weeks = weeks;
    }

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;	
	}
	
	public void calculateTotal(){
		int tot = 0;
		tot = Integer.parseInt(this.weeks.getW1())+Integer.parseInt(this.weeks.getW2())+Integer.parseInt(this.weeks.getW3())+Integer.parseInt(this.weeks.getW4());
		this.total = Integer.toString(tot);
	}

}
