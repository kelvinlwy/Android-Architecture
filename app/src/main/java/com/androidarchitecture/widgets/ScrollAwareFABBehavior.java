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
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

public class ScrollAwareFABBehavior extends FloatingActionButton.Behavior {
    public ScrollAwareFABBehavior(Context context, AttributeSet attrs) {
        super();
    }

    public boolean onStartNestedScroll(CoordinatorLayout parent, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
        return true;
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
        return dependency instanceof RecyclerView;
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout,
                               FloatingActionButton child, View target, int dxConsumed,
                               int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        // TODO Auto-generated method stub
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed,
                dxUnconsumed, dyUnconsumed);
        if (dyConsumed > 0 && child.getVisibility() == View.VISIBLE) {
            child.hide();
        } else if (dyConsumed < 0 && child.getVisibility() != View.VISIBLE) {
            child.show();
        }
    }
}
