package com.ccmedia;

import java.util.Vector;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * ViewGroup that arranges child views in a similar way to text, with them laid
 * out one line at a time and "wrapping" to the next line as needed.
 * 
 * Code licensed under CC-by-SA
 *  
 * @author Henrik Gustafsson
 * @see http://stackoverflow.com/questions/549451/line-breaking-widget-layout-for-android
 * @license http://creativecommons.org/licenses/by-sa/2.5/
 *
 */
public class PredicateLayout extends ViewGroup {

    private int line_height;

    public static class LayoutParams extends ViewGroup.LayoutParams {
        public final int horizontal_spacing;
        public final int vertical_spacing;

        /**
         * @param horizontal_spacing Pixels between items, horizontally
         * @param vertical_spacing Pixels between items, vertically
         */
        public LayoutParams(int horizontal_spacing, int vertical_spacing) {
            super(0, 0);
            this.horizontal_spacing = horizontal_spacing;
            this.vertical_spacing = vertical_spacing;     
        }
    }

    public PredicateLayout(Context context) {
        super(context);
    }

    public PredicateLayout(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        assert(MeasureSpec.getMode(widthMeasureSpec) != MeasureSpec.UNSPECIFIED);

        final int width = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft() - getPaddingRight();
        int height = MeasureSpec.getSize(heightMeasureSpec) - getPaddingTop() - getPaddingBottom();
        final int count = getChildCount();
        int line_height = 0;

        int xpos = getPaddingLeft();
        int ypos = getPaddingTop();

        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                final LayoutParams lp = (LayoutParams) child.getLayoutParams();
                
                int childHeightMeasureSpec;
                if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST) {
                    childHeightMeasureSpec =
                        MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST);
                }
                else {
                    childHeightMeasureSpec = 
                        MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);            
                }
                
                child.measure(
                        MeasureSpec.makeMeasureSpec(width, MeasureSpec.AT_MOST),
                        childHeightMeasureSpec);

               
                
                final int childw = child.getMeasuredWidth();
                line_height = child.getMeasuredHeight() + lp.vertical_spacing;//Math.max(line_height, child.getMeasuredHeight() + lp.vertical_spacing);
                
                if (xpos + childw > width) {
                    xpos = getPaddingLeft();
                    ypos += line_height;
                }

                xpos += childw + lp.horizontal_spacing;
            }
        }
        this.line_height = line_height;

        if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.UNSPECIFIED){
            height = ypos + line_height;

        } else if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST){
            if (ypos + line_height < height){
                height = ypos + line_height;
            }
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(1, 1); // default of 1px spacing
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        if (p instanceof LayoutParams)
            return true;
        return false;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int count = getChildCount();
        final int width = r - l;
        final int height = (b-getPaddingTop()) - t;
        int xpos = getPaddingLeft();
        int ypos = getPaddingTop();
        Vector<Integer> maxContentHeightByLine = new Vector<Integer>();

        int totalContentHeight = 0;
        int curMaxHeight = 0;
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                final int childw = child.getMeasuredWidth();
                final int childh = child.getMeasuredHeight();
                final LayoutParams lp = (LayoutParams) child.getLayoutParams();
                if(curMaxHeight < childh) curMaxHeight = childh;
                if (xpos + childw > width) {
                    xpos = getPaddingLeft();
                    System.out.println("add line height: "+line_height);
                    totalContentHeight += curMaxHeight;
                    maxContentHeightByLine.add(curMaxHeight);
                    curMaxHeight = 0;
                }
                xpos += childw + lp.horizontal_spacing;
            }
        }
        maxContentHeightByLine.add(curMaxHeight);
        totalContentHeight += curMaxHeight;
        
        System.out.println("maxContentHeightByLine: "+maxContentHeightByLine);
        
        xpos = getPaddingLeft();
        int lineCount = 0;
        curMaxHeight = maxContentHeightByLine.get(lineCount);
        
        System.out.println("l: "+l+", t: "+t+", r: "+r+", b:"+b);
        System.out.println("height: "+height);
        System.out.println("(height-totalContentHeight) / 2;: "+(height-totalContentHeight) / 2);
        
        ypos = (height-totalContentHeight) / 2;
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                final int childw = child.getMeasuredWidth();
                int childh = child.getMeasuredHeight();
                final LayoutParams lp = (LayoutParams) child.getLayoutParams();
                if (xpos + childw > width) {
                    xpos = getPaddingLeft();
                    ypos += line_height;
                    System.out.println("add line height: "+line_height);
                    
                    lineCount++;
                    curMaxHeight = maxContentHeightByLine.get(lineCount);
                }
                System.out.println("ypos: "+ypos);
                System.out.println("child height: "+childh);
                int bais = 0;//curMaxHeight-childh - 3;
                //bais = curMaxHeight-childh;
                
                child.layout(xpos, ypos+bais, xpos + childw, ypos + childh+bais+10);
                xpos += childw + lp.horizontal_spacing;
            }
        }
    }
}