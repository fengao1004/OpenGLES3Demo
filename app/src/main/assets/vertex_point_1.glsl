#version 300 es
layout (location = 0) in vec4 av_Position;
layout (location = 1) in vec4 point_Color;
out vec4 frag_point_Color;
void main() {
    gl_Position  = av_Position;
    frag_point_Color = point_Color;
    gl_PointSize = 10.0;
}
