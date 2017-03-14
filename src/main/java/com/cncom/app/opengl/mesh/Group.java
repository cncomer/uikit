package com.cncom.app.opengl.mesh;

import java.util.Vector;

import javax.microedition.khronos.opengles.GL10;

/**
 * 分组-group, group做的是分发所有的命令到它其中的子元素中
 * Created by bestjoy on 16/12/27.
 */

public class Group extends Mesh {
    private Vector<Mesh> children = new Vector<Mesh>();

    @Override
    public void draw(GL10 gl) {
        int size = children.size();
        for( int i = 0; i < size; i++)
            children.get(i).draw(gl);
    }

    public void add(int location, Mesh object) {
        children.add(location, object);
    }

    public boolean add(Mesh object) {
        return children.add(object);
    }

    public void clear() {
        children.clear();
    }

    public Mesh get(int location) {
        return children.get(location);
    }

    public Mesh remove(int location) {
        return children.remove(location);
    }

    public boolean remove(Object object) {
        return children.remove(object);
    }

    public int size() {
        return children.size();
    }
}
