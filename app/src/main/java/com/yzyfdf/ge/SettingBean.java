package com.yzyfdf.ge;

/**
 * Created by MLRC-iOS-CI on 2017/12/13.
 */

public class SettingBean {
    private String text;
    private int size;
    private PracticeModel mModel;
    private WriteStyle mStyle;

    public SettingBean(String text, int size, PracticeModel model, WriteStyle style) {
        this.text = text;
        this.size = size;
        mModel = model;
        mStyle = style;
    }

    public WriteStyle getStyle() {
        return mStyle;
    }

    public void setStyle(WriteStyle style) {
        mStyle = style;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public PracticeModel getModel() {
        return mModel;
    }

    public void setModel(PracticeModel model) {
        this.mModel = model;
    }

    enum WriteStyle {
        FANG("方格"),
        TIAN("田字格"),
        MI("米字格");

        private String name;

        WriteStyle(String name) {
            this.name = name;
        }

        public static WriteStyle getWriteStyle(int type) {
            for (WriteStyle style : values()) {
                if (style.ordinal() == type) {
                    return style;
                }
            }
            return MI;
        }
    }


    enum PracticeModel {
        SINGLE("单字"),
        ARTICLE("文章");

        private String name;

        PracticeModel(String name) {
            this.name = name;
        }

        public static PracticeModel getPracticeModel(int type) {
            for (PracticeModel model : values()) {
                if (model.ordinal() == type) {
                    return model;
                }
            }
            return ARTICLE;
        }
    }
}
