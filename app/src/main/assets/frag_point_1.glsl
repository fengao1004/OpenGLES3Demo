#version 300 es
precision mediump float;
out vec4 fragColor;
in vec4 frag_point_Color;
void main() {
    fragColor = frag_point_Color;
}
