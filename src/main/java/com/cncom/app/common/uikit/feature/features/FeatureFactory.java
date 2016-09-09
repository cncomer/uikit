package com.cncom.app.common.uikit.feature.features;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;

import com.cncom.app.common.uikit.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by bestjoy on 16/3/11.
 */
public class FeatureFactory {
    static final int PRIORITY_ABOVE_NORMAL = 750;
    static final int PRIORITY_BELOW_NORMAL = 250;
    static final int PRIORITY_HIGHEST = 1000;
    static final int PRIORITY_LOWEST = 0;
    static final int PRIORITY_NORMAL = 500;
    private static final HashMap<String, AttachInfo> featureMap = new HashMap<String, AttachInfo>();
    private static FeatureFactory mSelf = new FeatureFactory();

    static {
        featureMap.put("ClickDrawableMaskFeature", new AttachInfo(R.styleable.FeatureNameSpace_clickDrawableMaskFeature, PRIORITY_ABOVE_NORMAL));
        featureMap.put("RatioFeature", new AttachInfo(R.styleable.FeatureNameSpace_ratioFeature, PRIORITY_NORMAL));
        featureMap.put("RoundRectFeature", new AttachInfo(R.styleable.FeatureNameSpace_roundRectFeature, PRIORITY_NORMAL));
        featureMap.put("RoundFeature", new AttachInfo(R.styleable.FeatureNameSpace_roundFeature, PRIORITY_NORMAL));
        featureMap.put("ClickViewMaskFeature", new AttachInfo(R.styleable.FeatureNameSpace_clickViewMaskFeature, PRIORITY_BELOW_NORMAL));
        featureMap.put("BinaryPageFeature", new AttachInfo(R.styleable.FeatureNameSpace_uik_binaryPageFeature, PRIORITY_NORMAL));
        featureMap.put("PinnedHeaderFeature", new AttachInfo(R.styleable.FeatureNameSpace_pinnedHeaderFeature, PRIORITY_NORMAL));
        featureMap.put("PullToRefreshFeature", new AttachInfo(R.styleable.FeatureNameSpace_pullToRefreshFeature, PRIORITY_NORMAL));
        featureMap.put("StickyScrollFeature", new AttachInfo(R.styleable.FeatureNameSpace_stickyScrollFeature, PRIORITY_NORMAL));
        featureMap.put("ParallaxScrollFeature", new AttachInfo(R.styleable.FeatureNameSpace_parallaxScrollFeature, PRIORITY_NORMAL));
        featureMap.put("BounceScrollFeature", new AttachInfo(R.styleable.FeatureNameSpace_bounceScrollFeature, PRIORITY_NORMAL));
        featureMap.put("PencilShapeFeature", new AttachInfo(R.styleable.FeatureNameSpace_pencilShapeFeature, PRIORITY_NORMAL));
        featureMap.put("AutoScaleFeature", new AttachInfo(R.styleable.FeatureNameSpace_uik_autoScaleFeature, PRIORITY_NORMAL));
        featureMap.put("RotateFeature", new AttachInfo(R.styleable.FeatureNameSpace_rotateFeature, PRIORITY_NORMAL));
        featureMap.put("ImageSaveFeature", new AttachInfo(R.styleable.FeatureNameSpace_imagesavefeature, PRIORITY_NORMAL));
        featureMap.put("CellAnimatorFeature", new AttachInfo(R.styleable.FeatureNameSpace_cellAnimatorFeature, PRIORITY_NORMAL));
        featureMap.put("RecyclerCellAnimatorFeature", new AttachInfo(R.styleable.FeatureNameSpace_recyclerCellAnimatorFeature, PRIORITY_NORMAL));
        featureMap.put("DragToRefreshFeature", new AttachInfo(R.styleable.FeatureNameSpace_dragToRefreshFeature, PRIORITY_NORMAL));
        featureMap.put("ImageShapeFeature", new AttachInfo(R.styleable.FeatureNameSpace_imageShapeFeature, PRIORITY_NORMAL));
    }

    public FeatureFactory() {}

    public static <T extends View> ArrayList<AbsFeature<? super T>> creator(Context context, TypedArray typedArray) {
        String feature = "";
        int id = 0;
        ArrayList<AbsFeature<? super T>> arrayList = new ArrayList();
        Iterator entryIterator = featureMap.entrySet().iterator();
        while(entryIterator.hasNext()) {
            Map.Entry<String, AttachInfo> entry = (Map.Entry<String, AttachInfo>) entryIterator.next();
            feature = entry.getKey();
            id = entry.getValue().id;

            if (id > 0 && typedArray.getBoolean(id, false)) {
                try {
                    arrayList.add((AbsFeature<? super T>) Class.forName(mSelf.getClass().getPackage().getName() + "." + feature).newInstance());
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return arrayList;
    }

    public static int getFeaturePriority(String feature) {
        if (featureMap.containsKey(feature)) {
            return featureMap.get(feature).priority;
        }
        return 0;
    }

    private static class AttachInfo {
        int id;
        int priority;

        public AttachInfo(int id, int priority) {
            this.id = id;
            this.priority = priority;
        }
    }
}
