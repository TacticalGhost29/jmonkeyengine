/**
 * SchemaDuration.java
 *
 * This file was generated by XMLSpy 2007sp2 Enterprise Edition.
 *
 * YOU SHOULD NOT MODIFY THIS FILE, BECAUSE IT WILL BE
 * OVERWRITTEN WHEN YOU RE-RUN CODE GENERATION.
 *
 * Refer to the XMLSpy Documentation for further details.
 * http://www.altova.com/xmlspy
 */


package com.jmex.xml.types;

import java.text.DecimalFormat;


public class SchemaDuration extends SchemaCalendarBase {
  protected boolean bNegative;

  // construction
  public SchemaDuration() {
    setEmpty();
  }

  public SchemaDuration(SchemaDuration newvalue) {
    year = newvalue.year;
    month = newvalue.month;
    day = newvalue.day;
    hour = newvalue.hour;
    minute = newvalue.minute;
    second = newvalue.second;
    partsecond = newvalue.partsecond;
    hasTZ = newvalue.hasTZ;
    offsetTZ = newvalue.offsetTZ;
	bNegative = newvalue.isNegative();

    isempty = newvalue.isempty;
  }

  public SchemaDuration(int newyear, int newmonth, int newday, int newhour, int newminute, int newsecond, double newpartsecond, boolean newisnegative) {
    setYear(newyear);
    setMonth(newmonth);
    setDay(newday);
    setHour(newhour);
    setMinute(newminute);
    setSecond(newsecond);
    setPartSecond(newpartsecond);
    bNegative = newisnegative;

    isempty = false;
  }

  public SchemaDuration(String newvalue) {
    parse(newvalue);
  }

  public SchemaDuration(SchemaType newvalue) {
    assign( newvalue );
  }

  public SchemaDuration(SchemaTypeCalendar newvalue) {
    assign( (SchemaType)newvalue );
  }

  // setValue, getValue
  public int getYear() {
    return year;
  }

  public int getMonth() {
    return month;
  }

  public int getDay() {
    return day;
  }

  public int getHour() {
    return hour;
  }

  public int getMinute() {
    return minute;
  }

  public int getSecond() {
    return second;
  }

  public double getPartSecond() {
    return partsecond;
  }

  public int getMillisecond() {
    return (int)java.lang.Math.round(partsecond*1000.0);
  }

  public boolean isNegative() {
    return bNegative;
  }

  public void setYear(int newyear) {
    year = newyear;
    isempty = false;
  }

  public void setMonth(int newmonth) {
    month = newmonth;
    isempty = false;
  }

  public void setDay(int newday) {
    day = newday;
    isempty = false;
  }

  public void setHour(int newhour) {
    hour = newhour;
    isempty = false;
  }

  public void setMinute(int newminute) {
    minute = newminute;
    isempty = false;
  }

  public void setSecond(int newsecond) {
    second = newsecond;
    isempty = false;
  }

  public void setPartSecond(double newpartsecond) {
    partsecond = newpartsecond;
    isempty = false;
  }

  public void setMillisecond(int newmillisecond) {
    int normalizedMSec = newmillisecond;
    // must normallize, because 0 <= partseconds < 1
    if (normalizedMSec < 0) {
      int neededSeconds = newmillisecond / 1000 + 1;
      normalizedMSec = neededSeconds * 1000 + newmillisecond;
      if (!bNegative)
        second -= neededSeconds;
      else
        second += neededSeconds - 1;
    }
    if (normalizedMSec >= 1000) {
      int overflowSeconds = normalizedMSec / 1000;
      normalizedMSec = normalizedMSec % 1000;
      if (!bNegative)
        second += overflowSeconds;
      else
        second -= overflowSeconds;
    }
    partsecond = (double) normalizedMSec / 1000;
    isempty = false;
  }

  public void setNegative(boolean newisnegative) {
    bNegative = newisnegative;
    isempty = false;
  }


  public void assign(SchemaType newvalue) {
    if( newvalue == null || newvalue.isNull() || newvalue.isEmpty() )
      setEmpty();
    else if (newvalue instanceof SchemaDuration) {
      setInternalValues( ( (SchemaDuration) newvalue).year,
                        ( (SchemaDuration) newvalue).month,
                        ( (SchemaDuration) newvalue).day,
                        ( (SchemaDuration) newvalue).hour,
                        ( (SchemaDuration) newvalue).minute,
                        ( (SchemaDuration) newvalue).second,
                        ( (SchemaDuration) newvalue).partsecond, SchemaCalendarBase.TZ_MISSING, 0);
      bNegative = ( (SchemaDuration) newvalue).bNegative;
    }
    else if (newvalue instanceof SchemaString)
      parse(newvalue.toString());
    else
      throw new TypesIncompatibleException(newvalue, this);
  }

  public void setEmpty() {
    setInternalValues( 0,0,0, 0,0,0, 0.0, SchemaCalendarBase.TZ_MISSING, 0 );
    bNegative = false;
	isempty = true;
  }

  // further
  public Object clone() {
    return new SchemaDuration( this );
  }

  public String toString() {
    if( isempty )
      return "";

    StringBuffer s = new StringBuffer();
    if (bNegative)
      s.append("-");
    s.append("P");
    if (year != 0) {
      s.append(new DecimalFormat("0").format( (long) year));
      s.append("Y");
    }
    if (month != 0) {
      s.append(new DecimalFormat("0").format( (long) month));
      s.append("M");
    }
    if (day != 0) {
      s.append(new DecimalFormat("0").format( (long) day));
      s.append("D");
    }
	if (hour!=0 || minute!=0 || second!=0 || partsecond>0 )	{
    	s.append("T");
	    if (hour != 0) {
    		s.append(new DecimalFormat("0").format( (long) hour));
	    	s.append("H");
    	}
	    if (minute != 0) {
    		s.append(new DecimalFormat("0").format( (long) minute));
	    	s.append("M");
    	}
	    if (second != 0)
    		s.append(new DecimalFormat("0").format( (long) second));
	    if (partsecond > 0 && partsecond < 1) {
    		String sPartSecond = new DecimalFormat("0.0###############").format(partsecond);
	    	s.append(".");
    		s.append(sPartSecond.substring(2, sPartSecond.length()));
	    }
	    if (second != 0 || (partsecond > 0 && partsecond < 1))
    		s.append("S");
	}
    if (s.length() == 1)
		s.append("T0S");
    return s.toString();
  }

  public boolean booleanValue() {
    return true;
  }

  public void parse(String s) {
	  
	String newvalue = SchemaNormalizedString.normalize(SchemaNormalizedString.WHITESPACE_COLLAPSE, s);

	if ( newvalue == null )
	{
		isempty = true;
		return;
	}
	else if ( newvalue.length() == 0 )
	{
		isempty = true;
		return;
	}

	int pos = 0;
	bNegative = false;
	day = 0;
	hour = 0;
	minute = 0;
	second = 0;
	partsecond = 0.0;
	month = 0;	
	year = 0;
			
	if (newvalue.charAt(pos) == '-') 
	{
		bNegative = true;
		++pos;
	}

	if (pos == newvalue.length() || newvalue.charAt(pos) != 'P') 
		throw new StringParseException("Duration must start with P or -P followed by a duration value.", 2);
	++pos;

	int state = 0;	// year component
	while ( pos != newvalue.length() )
	{
		// no more data allowed?
		if (state == 8) 
			throw new StringParseException("Extra data after duration value.", 2);
						
		// check if ymd part is over
		if (newvalue.charAt(pos) == 'T') 
		{
			if (state >= 4) // hour
				throw new StringParseException("Multiple Ts inside duration value.", 2);
			state = 4;
			++pos;			
		}

		if (state == 3) // 'T' separator
			throw new StringParseException("Extra data after duration value.", 2);

		// now a digit has to follow, and probably more than one
		if (pos == newvalue.length() || newvalue.charAt(pos) < '0' || newvalue.charAt(pos) > '9') 
			throw new StringParseException("Invalid numeric data in duration value.", 2);

		int val = 0;
		int digits = 0;
		while (pos != newvalue.length() && newvalue.charAt(pos) >= '0' && newvalue.charAt(pos) <= '9')
		{
			if (val >= 100000000) 
				throw new StringParseException("Numeric overflow in duration value.", 2);
			val = val * 10 + (newvalue.charAt(pos) - '0');
			digits += 1;
			++pos;
		}

		if (pos == newvalue.length()) 
			throw new StringParseException("Duration value missing component designator.", 2);
				
		int foundState = 8;	// bad
		switch (newvalue.charAt(pos)) 
		{
		case 'Y': if (state >= 4) foundState = 8; else foundState = 0; break;
		case 'M': if (state >= 4) foundState = 5; else foundState = 1; break;
		case 'D': if (state >= 4) foundState = 8; else foundState = 2; break;
		case 'H': if (state >= 4) foundState = 4; else foundState = 8; break;
		case 'S': if (state >= 7) foundState = 7; else if (state >= 4) foundState = 6; else foundState = 8; break;
		case '.': if (state >= 4) foundState = 6; else foundState = 8; break;
		}

		if (foundState == 8 || foundState < state) 
			throw new StringParseException("Invalid or duplicate component designator.", 2);

		++pos;

		switch ( foundState )
		{
		case 0: year = val; break;
		case 1: month = val; break;
		case 2: day = val; break;
		case 4: hour = val; break;
		case 5: minute = val; break;
		case 6: second = val; break;
		case 7: partsecond = val * Math.pow(0.1, digits); break;
		}

		state = foundState + 1;
	}					
	if (state == 0) 
		throw new StringParseException("No components given after P in duration value.", 2);

	isempty = false;

  }

  // ---------- interface SchemaTypeCalendar ----------
  public int calendarType() {
    return CALENDAR_VALUE_DURATION;
  }

  public SchemaDuration durationValue() {
    return new SchemaDuration( this );
  }

  public SchemaDateTime dateTimeValue() {
    throw new TypesIncompatibleException( this, new SchemaDateTime( "2003-07-28T12:00:00" ) );
  }

  public SchemaDate dateValue() {
    throw new TypesIncompatibleException( this, new SchemaDate( "2003-07-28" ) );
  }

  public SchemaTime timeValue() {
    throw new TypesIncompatibleException( this, new SchemaTime( "12:00:00" ) );
  }

  public long getDayTimeValue() {
    int sign = bNegative ? -1 : +1;
    return (long)(((((long)day * 24 + hour) * 60 + minute) * 60 + second + partsecond) * 1000) * sign;
  }

  public int getYearMonthValue() {
    int sign = bNegative ? -1 : +1;
    return (year * 12 + month) * sign;
  }

  public void setYearMonthValue(int l) {
	if (l < 0) {
		bNegative = true;
		l = -l;
	} else {
		bNegative = false;
	}

    year = l / 12;
    month = l % 12;
  }

  public void setDayTimeValue(long l) {
	if (l < 0) {
		bNegative = true;
		l = -l;
	} else {
		bNegative = false;
	}
    day = (int)(l / 86400000l); l %= 86400000l;
    hour = (int)(l / 3600000l); l %=  3600000l;
    minute = (int)(l / 60000l); l %=    60000l;
    second =  (int)(l / 1000l); l %=     1000l;
    partsecond = l / 1000.0;
  }
}
