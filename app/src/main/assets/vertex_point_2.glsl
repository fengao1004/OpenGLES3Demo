#version 300 es
layout (location = 0) in vec4 av_Position;
layout (location = 1) in float point_Size;
void main() {
    gl_Position  = av_Position;
    gl_PointSize = point_Size;
}
