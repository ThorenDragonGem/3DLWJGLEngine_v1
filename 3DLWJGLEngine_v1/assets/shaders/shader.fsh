#version 420 core

in vec2 outTexCoord;
in vec3 exColor;
out vec4 fragColour;

uniform int isTextured;
uniform float alpha;
uniform sampler2D texture_sampler;

void main()
{
	if(isTextured == 1)
	{
		fragColor = texture(texture_sampler, outTexCoord);
	}
	else
	{
		fragColor = vec4(exColor, alpha);
	}
}
