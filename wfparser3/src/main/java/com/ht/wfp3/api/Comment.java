package com.ht.wfp3.api;

/**
 * The comment statement. Although the specification is somewhat ambiguous, it is assumed that
 * comments can actually appear anywhere, including trailing after any statement in the OBJ file or
 * alone on a blank line.
 * 
 * 
 * Comments
 * 
 * Comments can appear anywhere in an .obj file. They are used to annotate the file; they are not
 * processed.
 * 
 * Here is an example:
 * 
 * # this is a comment
 * 
 * The Model program automatically inserts comments when it creates .obj files. For example, it
 * reports the number of geometric vertices, texture vertices, and vertex normals in a file.
 * 
 * # 4 vertices # 4 texture vertices # 4 normals
 * 
 * @author nickl
 *
 */
public interface Comment extends Statement {

}
