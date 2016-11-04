/*
 * Copyright (C) 2016 Kelvin Leung Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.androidarchitecture.widgets;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};
    private Drawable mDivider;

    public DividerItemDecoration(Context context) {
        try {
            final TypedArray a = context.obtainStyledAttributes(ATTRS);
            mDivider = a.getDrawable(0);
            a.recycle();
        } catch (Resources.NotFoundException e) {
            // TODO Log or handle as necessary.
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (mDivider == null) return;

        final int itemCount = state.getItemCount();

        // first position
        if (parent.getChildAdapterPosition(view) == 0) return;
        // last position
        if (itemCount > 0 && parent.getChildAdapterPosition(view) >= itemCount - 1) return;

        if (getOrientation(parent) == LinearLayoutManager.VERTICAL)
            outRect.top = mDivider.getIntrinsicHeight();
        else
            throw new IllegalArgumentException("Only usable with vertical lists");
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mDivider == null) {
            super.onDrawOver(c, parent, state);
            return;
        }

        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; ++i) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int size = mDivider.getIntrinsicHeight();
            final int top = (int) (child.getTop() - params.topMargin - size + child.getTranslationY());
            final int bottom = top + size;
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);

            if (i == childCount - 1) {
                final int newTop = (int) (child.getBottom() + params.bottomMargin + child.getTranslationY());
                final int newBottom = newTop + size;
                mDivider.setBounds(left, newTop, right, newBottom);
                mDivider.draw(c);
            }
        }
    }

    private int getOrientation(RecyclerView parent) {
        if (!(parent.getLayoutManager() instanceof LinearLayoutManager))
            throw new IllegalStateException("Layout manager must be an instance of LinearLayoutManager");
        return ((LinearLayoutManager) parent.getLayoutManager()).getOrientation();
    }
}
