/**
 * Pruebas unitarias para el algoritmo de Huffman.
 * @Author: Carlos Delgado
 * @Version: 1
 * @Date: 03/JUL/2023
 */
package project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class TestHuffman {

  private ClassLoader classLoader = getClass().getClassLoader();

  /**
   * Verifica que el árbol de Huffman sea válido.
   * @param tree árbol de Huffman
   * @return true si el árbol es válido, false de lo contrario
   */
  public boolean verifyTree(HuffmanBinaryTree tree){
    int key = tree.getNumberKey();
    if (key != -1){
      HuffmanBinaryTree left = tree.getLeft();
      HuffmanBinaryTree right = tree.getRight();

      if (left != null && right != null){
        boolean condition = key >= left.getNumberKey() && key >= right.getNumberKey();
        return condition && verifyTree(left) && verifyTree(right);
      }
      else{
        if (left == null){
          boolean condition = key >= right.getNumberKey();
          return condition && verifyTree(right);
        }
        else {
          if (right == null){
            boolean condition = key >= left.getNumberKey();
            return condition && verifyTree(left);
          }
          else{
            return true;
          }
        }
      }
    }
    else{
      return true;
    }
  }

  private String loadFile(String fileName){
    try{
      Path filePath = Path.of(classLoader.getResource(fileName).getFile());
      return Files.readString(filePath, StandardCharsets.UTF_8);
    }
    catch(IOException e){
      return null;
    }

  }

  @Test
  public void testFile1() {
    //Setup
    HuffmanCoding coding = new HuffmanCoding();
    String text = loadFile("ejemplo1.in");
    HuffmanDecoding decoding = new HuffmanDecoding();

    //Execute
    String encoded = coding.encode(text);
    HuffmanBinaryTree tree = coding.getTree();
    String decoded = decoding.decode(encoded, tree);

    //Assert
    assertTrue(verifyTree(tree));
    assertEquals(text, decoded);
  }

  @Test
  public void testFile2() {
    //Setup
    HuffmanCoding coding = new HuffmanCoding();
    String text = loadFile("ejemplo2.in");
    HuffmanDecoding decoding = new HuffmanDecoding();

    //Execute
    String encoded = coding.encode(text);
    HuffmanBinaryTree tree = coding.getTree();
    String decoded = decoding.decode(encoded, tree);

    //Assert
    assertTrue(verifyTree(tree));
    assertEquals(text, decoded);
  }

  @Test
  public void testFile3() {
    //Setup
    HuffmanCoding coding = new HuffmanCoding();
    String text = loadFile("ejemplo3.in");
    System.out.println(text);
    HuffmanDecoding decoding = new HuffmanDecoding();

    //Execute
    String encoded = coding.encode(text);
    HuffmanBinaryTree tree = coding.getTree();
    String decoded = decoding.decode(encoded, tree);

    //Assert
    assertTrue(verifyTree(tree));
    assertEquals(text, decoded);
  }
  
}
