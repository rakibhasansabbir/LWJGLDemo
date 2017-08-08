/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lwjgldemo;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import sun.misc.Version;

/**
 *
 * @author rakib
 */
public class Render {

    private long window;
    private float angle;
    private float speed;

    public Render() {
        angle = 0;
        speed = 1;
    }

    public void run() {
        init();
        loop();
    }

    private void init() {
        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("Could not Initialize GLFW");
        }
        window = GLFW.glfwCreateWindow(400, 400, "LWJGL Demo", 0, 0);

        GLFW.glfwSetKeyCallback(window,(window, key, scandle, action, mods) -> {
            if (key == GLFW.GLFW_KEY_ESCAPE && action == GLFW.GLFW_RELEASE){
                GLFW.glfwSetWindowShouldClose(window,true);
            }else if (key == GLFW.GLFW_KEY_UP && action != GLFW.GLFW_RELEASE){
                speed = speed + 0.1f;
            }else if (key == GLFW.GLFW_KEY_DOWN && action != GLFW.GLFW_RELEASE){
                speed = speed - 0.1f;
            }else if (key == GLFW.GLFW_KEY_SPACE && action != GLFW.GLFW_RELEASE){
                speed = 0;
            }
        });
        GLFW.glfwMakeContextCurrent(window);
    }

    private void loop() {
        GL.createCapabilities();
        while (!GLFW.glfwWindowShouldClose(window)) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
            drawSomething();
            GLFW.glfwSwapBuffers(window);
            GLFW.glfwPollEvents();
        }

    }

    private void drawSomething() {
        GL11.glPushMatrix();
        GL11.glRotated(angle, 0, 0, 1);
        GL11.glTranslated(0.25, 0, 0);
        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex3f(-0.1f, 0, 0);
        GL11.glVertex3f(+0.4f, 0, 0);

//        GL11.glVertex3f(0.4f, -0.1f, 0);
//        GL11.glVertex3f(0.4f, +0.1f, 0);
        GL11.glVertex3f(+0.5f, 0, 0);
        GL11.glVertex3f(0.4f, +0.1f, 0);

        GL11.glVertex3f(+0.5f, 0, 0);
        GL11.glVertex3f(0.4f, -0.1f, 0);

        GL11.glEnd();
        GL11.glPopMatrix();

        angle -= speed;

    }
}
