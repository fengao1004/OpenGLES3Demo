#version 300 es
layout (location = 0) in vec4 av_Position;
void main() {
    gl_Position  = av_Position;
    gl_PointSize = 10.0;
}
