/**
 *
 * Copyright 2014 Cisco. All rights reserved.
 * MenuItem.java
 *
 */
package com.cisco.j4atool;

import android.graphics.drawable.Drawable;

/**
 * 
  *
  * @ClassName: MyMenuItem
  * @Description: 
  *
  * @Author: kevin
  * @Date: 2014年7月10日 
  *
 */
public class MyMenuItem
{
    private Drawable icon;

    private String name;

    public Drawable getIcon()
    {
        return icon;
    }

    public void setIcon(Drawable icon)
    {
        this.icon = icon;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

}
