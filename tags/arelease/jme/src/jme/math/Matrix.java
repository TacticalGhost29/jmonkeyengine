/*
 * Copyright (c) 2003, jMonkeyEngine - Mojo Monkey Coding
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this 
 * list of conditions and the following disclaimer. 
 * 
 * Redistributions in binary form must reproduce the above copyright notice, 
 * this list of conditions and the following disclaimer in the documentation 
 * and/or other materials provided with the distribution. 
 * 
 * Neither the name of the Mojo Monkey Coding, jME, jMonkey Engine, nor the 
 * names of its contributors may be used to endorse or promote products derived 
 * from this software without specific prior written permission. 
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE 
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 * POSSIBILITY OF SUCH DAMAGE.
 *
 */
package jme.math;

import jme.exception.MonkeyRuntimeException;

/**
 * <code>Matrix</code> defines and maintains a 4x4 matrix. This matrix is
 * intended for use in a translation and rotational capacity. It provides
 * convinience methods for creating the matrix from a multitude of sources.
 * 
 * @author Mark Powell
 * @version $Id: Matrix.java,v 1.1.1.1 2003-10-29 10:58:50 Anakan Exp $
 */
public class Matrix {
    public float matrix[][];
    
    /**
     * Constructor instantiates a new <code>Matrix</code> that is set to the
     * identity matrix.
     *
     */
    public Matrix() {
        loadIdentity();
    }
    
    /**
     * Constructor instantiates a new <code>Matrix</code> that is set to the
     * provided matrix. This constructor copies a given Matrix. If the 
     * provided matrix is null, the constructor sets the matrix to the 
     * identity.
     * @param mat the matrix to copy.
     */
    public Matrix(Matrix mat) {
    	if(null == mat) {
    		loadIdentity();
    	} else {
	    	for(int i = 0; i < 4; i++) {
                for(int j = 0; j < 4; j++) {
	    		 matrix[i][j] = mat.getMatrix()[i][j];
	    	  }
            }
    	}
    }

    /**
     * <code>loadIdentity</code> sets this matrix to the identity matrix, 
     * namely all zeros with ones along the diagonal.
     *
     */
    public void loadIdentity() {
        matrix = new float[4][4];
        matrix[0][0] = matrix[1][1] = matrix[2][2] = matrix[3][3] = 1;
    }

    /**
     * <code>set</code> sets the values of this matrix from an array of
     * values.
     * @param matrix the matrix to set the value to.
     * @throws MonkeyRuntimeException if the array is not of size 16.
     */
    public void set(float[][] matrix) {
        if (matrix.length != 4 || matrix[0].length != 4) {
            throw new MonkeyRuntimeException("Array must be of size 16.");
        }

        for (int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++ ) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
    }
    
    public void set(float[] matrix) {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                this.matrix[i][j] = matrix[j*4 + i];
            }
        }
    }
    
    public void set(Quaternion quaternion) {
        matrix[0][0] = (float) (1.0 - 2.0 * quaternion.y * quaternion.y - 2.0 * quaternion.z * quaternion.z);
        matrix[1][0] = (float) (2.0 * quaternion.x * quaternion.y + 2.0 * quaternion.w * quaternion.z);
        matrix[2][0] = (float) (2.0 * quaternion.x * quaternion.z - 2.0 * quaternion.w * quaternion.y);

        matrix[0][1] = (float) (2.0 * quaternion.x * quaternion.y - 2.0 * quaternion.w * quaternion.z);
        matrix[1][1] = (float) (1.0 - 2.0 * quaternion.x * quaternion.x - 2.0 * quaternion.z * quaternion.z);
        matrix[2][1] = (float) (2.0 * quaternion.y * quaternion.z + 2.0 * quaternion.w * quaternion.x);

        matrix[0][2] = (float) (2.0 * quaternion.x * quaternion.z + 2.0 * quaternion.w * quaternion.y);
        matrix[1][2] = (float) (2.0 * quaternion.y * quaternion.z - 2.0 * quaternion.w * quaternion.x);
        matrix[2][2] = (float) (1.0 - 2.0 * quaternion.x * quaternion.x - 2.0 * quaternion.y * quaternion.y);
    
    }
    
    public void copy(Matrix matrix) {
        if(null == matrix) {
            loadIdentity();
        } else {
            for(int i = 0; i < 4; i++) {
                for(int j = 0; j < 4; j++) {
                 this.matrix[i][j] = matrix.matrix[i][j];
              }
            }
        }
    }
    
    /**
     * <code>add</code> adds the values of a parameter matrix to this matrix.
     * @param matrix the matrix to add to this.
     */
    public void add(Matrix matrix) {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                this.matrix[i][j] += matrix.getMatrix()[i][j];
            }
        }
    }
    
    /**
     * <code>multiply</code> multiplies this matrix by a scalar.
     * @param scalar the scalar to multiply this matrix by.
     */
    public void multiply(float scalar) {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                matrix[i][j] *= scalar;
            }
        }
    }

    /**
     * <code>multiply</code> multiplies this matrix with another matrix. The
     * result matrix will then be returned.
     * This matrix will be on the left hand side, while the parameter matrix
     * will be on the right.
     * @param in2 the matrix to multiply this matrix by.
     * @return the resultant matrix
     * @throws MonkeyRuntimeException if matrix is null.
     */
    public Matrix multiply(Matrix in2) {
        Matrix out = new Matrix();
        out.matrix[0][0] = matrix[0][0] * in2.matrix[0][0] + matrix[0][1] * in2.matrix[1][0] + matrix[0][2] * in2.matrix[2][0];
        out.matrix[0][1] = matrix[0][0] * in2.matrix[0][1] + matrix[0][1] * in2.matrix[1][1] + matrix[0][2] * in2.matrix[2][1];
        out.matrix[0][2] = matrix[0][0] * in2.matrix[0][2] + matrix[0][1] * in2.matrix[1][2] + matrix[0][2] * in2.matrix[2][2];
        out.matrix[0][3] = matrix[0][0] * in2.matrix[0][3] + matrix[0][1] * in2.matrix[1][3] + matrix[0][2] * in2.matrix[2][3] + matrix[0][3];
        out.matrix[1][0] = matrix[1][0] * in2.matrix[0][0] + matrix[1][1] * in2.matrix[1][0] + matrix[1][2] * in2.matrix[2][0];
        out.matrix[1][1] = matrix[1][0] * in2.matrix[0][1] + matrix[1][1] * in2.matrix[1][1] + matrix[1][2] * in2.matrix[2][1];
        out.matrix[1][2] = matrix[1][0] * in2.matrix[0][2] + matrix[1][1] * in2.matrix[1][2] + matrix[1][2] * in2.matrix[2][2];
        out.matrix[1][3] = matrix[1][0] * in2.matrix[0][3] + matrix[1][1] * in2.matrix[1][3] + matrix[1][2] * in2.matrix[2][3] + matrix[1][3];
        out.matrix[2][0] = matrix[2][0] * in2.matrix[0][0] + matrix[2][1] * in2.matrix[1][0] + matrix[2][2] * in2.matrix[2][0];
        out.matrix[2][1] = matrix[2][0] * in2.matrix[0][1] + matrix[2][1] * in2.matrix[1][1] + matrix[2][2] * in2.matrix[2][1];
        out.matrix[2][2] = matrix[2][0] * in2.matrix[0][2] + matrix[2][1] * in2.matrix[1][2] + matrix[2][2] * in2.matrix[2][2];
        out.matrix[2][3] = matrix[2][0] * in2.matrix[0][3] + matrix[2][1] * in2.matrix[1][3] + matrix[2][2] * in2.matrix[2][3] + matrix[2][3];
        out.matrix[3][0] =
                    this.matrix[0][0] * in2.getMatrix()[3][0]
                        + this.matrix[1][0] * in2.getMatrix()[3][1]
                        + this.matrix[2][0] * in2.getMatrix()[3][2]
                        + this.matrix[3][0];
                out.matrix[3][1] =
                    this.matrix[0][1] * in2.getMatrix()[3][0]
                        + this.matrix[1][1] * in2.getMatrix()[3][1]
                        + this.matrix[2][1] * in2.getMatrix()[3][2]
                        + this.matrix[3][1];
                out.matrix[3][2] =
                    this.matrix[0][2] * in2.getMatrix()[3][0]
                        + this.matrix[1][2] * in2.getMatrix()[3][1]
                        + this.matrix[2][2] * in2.getMatrix()[3][2]
                        + this.matrix[3][2];
                out.matrix[3][3] = 1;
        return out;
    }
    
    /**
     * <code>setTranslation</code> will set the matrix's translation values.
     * @param translation the new values for the translation.
     * @throws MonkeyRuntimeException if translation is not size 3.
     */
    public void setTranslation(float[] translation) {
        if (translation.length != 3) {
            throw new MonkeyRuntimeException("Translation size must be 3.");
        }
        matrix[3][0] = translation[0];
        matrix[3][1] = translation[1];
        matrix[3][2] = translation[2];
    }

    /**
     * <code>setInverseTranslation</code> will set the matrix's inverse 
     * translation values.
     * @param translation the new values for the inverse translation.
     * @throws MonkeyRuntimeException if translation is not size 3.
     */
    public void setInverseTranslation(float[] translation) {
        if (translation.length != 3) {
            throw new MonkeyRuntimeException("Translation size must be 3.");
        }
        matrix[3][0] = -translation[0];
        matrix[3][1] = -translation[1];
        matrix[3][2] = -translation[2];
    }

    public void angleRotationRadians(Vector angles) {
        float sr, sp, sy, cr, cp, cy;

        sy = (float) java.lang.Math.sin(angles.z);
        cy = (float) java.lang.Math.cos(angles.z);
        sp = (float) java.lang.Math.sin(angles.y);
        cp = (float) java.lang.Math.cos(angles.y);
        sr = (float) java.lang.Math.sin(angles.x);
        cr = (float) java.lang.Math.cos(angles.x);

        // matrix = (Z * Y) * X
        matrix[0][0] = cp * cy;
        matrix[1][0] = cp * sy;
        matrix[2][0] = -sp;
        matrix[0][1] = sr * sp * cy + cr * -sy;
        matrix[1][1] = sr * sp * sy + cr * cy;
        matrix[2][1] = sr * cp;
        matrix[0][2] = (cr * sp * cy + -sr * -sy);
        matrix[1][2] = (cr * sp * sy + -sr * cy);
        matrix[2][2] = cr * cp;
        matrix[0][3] = 0.0f;
        matrix[1][3] = 0.0f;
        matrix[2][3] = 0.0f;
    }
    
    public void angleRotationDegrees(Vector angles) {
        float angle;
        float sr, sp, sy, cr, cp, cy;

        angle = (float) (angles.z * (Math.PI * 2 / 360));
        sy = (float) java.lang.Math.sin(angle);
        cy = (float) java.lang.Math.cos(angle);
        angle = (float) (angles.y * (Math.PI * 2 / 360));
        sp = (float) java.lang.Math.sin(angle);
        cp = (float) java.lang.Math.cos(angle);
        angle = (float) (angles.x * (Math.PI * 2 / 360));
        sr = (float) java.lang.Math.sin(angle);
        cr = (float) java.lang.Math.cos(angle);

        // matrix = (Z * Y) * X
        matrix[0][0] = cp * cy;
        matrix[1][0] = cp * sy;
        matrix[2][0] = -sp;
        matrix[0][1] = sr * sp * cy + cr * -sy;
        matrix[1][1] = sr * sp * sy + cr * cy;
        matrix[2][1] = sr * cp;
        matrix[0][2] = (cr * sp * cy + -sr * -sy);
        matrix[1][2] = (cr * sp * sy + -sr * cy);
        matrix[2][2] = cr * cp;
        matrix[0][3] = 0.0f;
        matrix[1][3] = 0.0f;
        matrix[2][3] = 0.0f;
    }

    /**
     * <code>setRotationQuaternion</code> builds a rotation from a 
     * <code>Quaternion</code>.
     * @param quat the quaternion to build the rotation from.
     * @throws MonkeyRuntimeException if quat is null.
     */
    public void setRotationQuaternion(Quaternion quat) {
        if(null == quat) {
            throw new MonkeyRuntimeException("Quat may not be null.");
        }
        matrix[0][0] =
            (float) (1.0 - 2.0 * quat.y * quat.y - 2.0 * quat.z * quat.z);
        matrix[0][1] = (float) (2.0 * quat.x * quat.y + 2.0 * quat.w * quat.z);
        matrix[0][2] = (float) (2.0 * quat.x * quat.z - 2.0 * quat.w * quat.y);

        matrix[1][0] = (float) (2.0 * quat.x * quat.y - 2.0 * quat.w * quat.z);
        matrix[1][1] =
            (float) (1.0 - 2.0 * quat.x * quat.x - 2.0 * quat.z * quat.z);
        matrix[1][2] = (float) (2.0 * quat.y * quat.z + 2.0 * quat.w * quat.x);

        matrix[2][0] = (float) (2.0 * quat.x * quat.z + 2.0 * quat.w * quat.y);
        matrix[2][1] = (float) (2.0 * quat.y * quat.z - 2.0 * quat.w * quat.x);
        matrix[2][2] =
            (float) (1.0 - 2.0 * quat.x * quat.x - 2.0 * quat.y * quat.y);
    }

    /**
     * <code>setInverseRotationRadians</code> builds an inverted rotation
     * from Euler angles that are in radians.
     * @param angles the Euler angles in radians.
     * @throws MonkeyRuntimeException if angles is not size 3.
     */
    public void setInverseRotationRadians(float[] angles) {
        if (angles.length != 3) {
            throw new MonkeyRuntimeException("Angles must be of size 3.");
        }
        double cr = Math.cos(angles[0]);
        double sr = Math.sin(angles[0]);
        double cp = Math.cos(angles[1]);
        double sp = Math.sin(angles[1]);
        double cy = Math.cos(angles[2]);
        double sy = Math.sin(angles[2]);

        matrix[0][0] = (float) (cp * cy);
        matrix[1][0] = (float) (cp * sy);
        matrix[2][0] = (float) (-sp);

        double srsp = sr * sp;
        double crsp = cr * sp;

        matrix[0][1] = (float) (srsp * cy - cr * sy);
        matrix[1][1] = (float) (srsp * sy + cr * cy);
        matrix[2][1] = (float) (sr * cp);

        matrix[0][2] = (float) (crsp * cy + sr * sy);
        matrix[1][2] = (float) (crsp * sy - sr * cy);
        matrix[2][2] = (float) (cr * cp);
    }

    /**
     * <code>setInverseRotationDegrees</code> builds an inverted rotation
     * from Euler angles that are in degrees.
     * @param angles the Euler angles in degrees.
     * @throws MonkeyRuntimeException if angles is not size 3.
     */
    public void setInverseRotationDegrees(float[] angles) {
        if (angles.length != 3) {
            throw new MonkeyRuntimeException("Angles must be of size 3.");
        }
        float vec[] = new float[3];
        vec[0] = (float) (angles[0] * 180.0 / Math.PI);
        vec[1] = (float) (angles[1] * 180.0 / Math.PI);
        vec[2] = (float) (angles[2] * 180.0 / Math.PI);
        setInverseRotationRadians(vec);
    }

    /**
     * <code>getMatrix</code> returns the current matrix as an array of
     * floats. Size 16.
     * @return the array of floats that represent this matrix.
     */
    public float[][] getMatrix() {
        return matrix;
    }
    
    /**
     * 
     * <code>inverseTranslateVect</code> translates a given vector by the
     * translation part of this matrix.
     * @param vector the vector to be translated.
     * @throws MonkeyRuntimeException if the size of the vector is not 3.
     */
    public void inverseTranslateVect(float[] vector) {
        if (vector.length != 3) {
            throw new MonkeyRuntimeException("Vector must be of size 3.");
        }

        vector[0] = vector[0] - matrix[3][0];
        vector[1] = vector[1] - matrix[3][1];
        vector[2] = vector[2] - matrix[3][2];
    }

    /**
     * 
     * <code>inverseRotateVect</code> rotates a given vector by the rotation
     * part of this matrix.
     * @param vector the vector to be rotated.
     * @throws MonkeyRuntimeException if the size of the vector is not 3.
     */
    public void inverseRotateVect(float[] vector) {
        if (vector.length != 3) {
            throw new MonkeyRuntimeException("Vector must be of size 3.");
        }

        vector[0] =
            vector[0] * matrix[0][0]
                + vector[1] * matrix[0][1]
                + vector[2] * matrix[0][2];
        vector[1] =
            vector[0] * matrix[1][0]
                + vector[1] * matrix[1][1]
                + vector[2] * matrix[1][2];
        vector[2] =
            vector[0] * matrix[2][0]
                + vector[1] * matrix[2][1]
                + vector[2] * matrix[2][2];
    }
    
    public void tensorProduct (Vector u, Vector v) {
        matrix[0][0] = u.x * v.x;
        matrix[0][1] = u.x * v.y;
        matrix[0][2] = u.x * v.z;
        matrix[1][0] = u.y * v.x;
        matrix[1][1] = u.y * v.y;
        matrix[1][2] = u.y * v.z;
        matrix[2][0] = u.z * v.x;
        matrix[2][1] = u.z * v.y;
        matrix[2][2] = u.z * v.z;
    }
}