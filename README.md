# Blockchain - Ajtai Hash Function

Blockchain Simulation using Lattice-Based Ajtai Hash Function

## Introduction

In this project, which is part of a Bachelor's thesis in Computer Science on the theme of *post-quantum cryptography*,
it is proposed to use a lattice-based hash function, better known as the *Ajtai hash function*,
instead of the standard SHA-256.

## Description

The blockchain simulation is composed of the following five Java classes.

- **Transaction.java**

   A transaction simply consists of the next elements.
  
   - Identifier
   - Name of the sender
   - Name of the receiver
   - Amount
   
- **Utility.java**

  This class is only used as a helper containing the definitions of two essential methods in the mining process, namely the Merkle root computation (*computeMerkleRoot*) and the Ajtai hash function computation (*AjtaiHash*). The purpose of this class is to avoid duplicating the code of these methods in the *Block* and *Miner* classes.
  
  The *Merkle root* is computed in the usual way using the transaction's identifiers as leaves, while the Ajtai hash function is parametrized by the three following integers which define a random *n* x *m* binary matrix, called the *Ajtai key*.
  
   - *n*    :     length of the output hash value (hexadecimal representation)
   - *m*    :     maximal length of the input string (binary representation)
   - *q*    :     modulus
 
   The Ajtai hash function is then defined as
   
   <p align="center">
      <img src="https://github.com/WatiBenj/Blockchain_Ajtai_Hash/blob/master/Ajtai_Hash_Function.PNG" width="410" height="100">
   </p>
   
   where the *n* x *m* binary matrix *A* is the *Ajtai key*.
   
- **Block.java**

  The necessary fields for the complete representation of a block are as follows.
  
   - Hash of the previous block
   - Merkle root (computed from a set of transactions)
   - Nonce (random integer)
   - Hash of the block (AjtaiHash(previous hash + Merkle root + nonce))
   - Timestamp (date + time)
   - Name of the miner
   
  The first block is called the \emph{genesis} block and since it has no previous block, its previous hash value is randomly generated.

- **Miner.java**

  To fully represent a miner, the next attributes are required.
  
   - Name of the miner
   - Own copy of the Ajtai key
   - Own copy of the blockchain
   - Reward (amount of bitcoins)
 
  This class is also equipped with two essential methods concerning the mining of a block (*mineBlock*) and the verification of the blockchain's validity (*checkChainValidity*). A single call to *mineBlock* generates a single hash value counting as an attempt to constitute the proof of work. The validity of the blockchain is checked by each miner after adding a new mined block. This method simply loops through all the blocks in the chain, then compares the hashes and verifies the proof work. This verification procedure includes the next three steps.
  
   - Comparison of the hash of the previous block and the previous hash of the current block
   - Comparison of the registered hash of the current block and the newly computed hash of the current block
   - Verification of the proof of work (check if the hash of the current block starts with the correct number of 0's)

 - **Main.java**
 
   This class can be seen as the environment in which all actions related to the extraction of a new block and its addition to the blockchain are undertaken. Its fields are the parameters *n*, *m* and *q* of the Ajtai hash function, the corresponding *Ajtai key* and an integer *difficulty* specifying the required number of 0's with which the correct hash value should start. The *main* method contains all the stages required to add a new block to the blockchain.
   
 More precisely, the addition of a new block can be described by the following steps.
  
  1. Definition of the Ajtai key (random binary matrix)
  2. Instanciation of the first block (genesis)
  3. Addition of the genesis block to the (empty) blockchain
  4. Instanciation of an arbitrary number of miners with their own copy of the Ajtai key and the initial blockchain
  5. Instanciation of an arbitrary number of transactions (preferably a power of two to facilitate the Merkle root computation)
  6. Computation of the Merkle root from the set of transactions by each miner
  7. Mining process :
     - Each miner attempts successively to mine the next block by applying the Ajtai hash function on the concatenation of the hash of the previous bloc, the Merkle root and a nonce random nonce value
     - This step is repeated many times by randomly varying the nonce value until the proof of work is established (hash must begin with the correct number of 0's specified by the parameter *difficulty*)
     - The process ends immediately as soon as one of the miners obtains the desired hash value
  8. Addition of the new mined block to the blockchain of each miner
  9. Verification of the blockchain's validity by each miner
  10. Attribution of a given number of bitcoins (reward) to the miner who mined the new block
 
 In the mining process, it is assumed that all Ajtai hash calls have the same duration. Therefore, competition between miners may simply be simulated as a succession of each miner's attempt. In other words, each miner has only one try in each round and must wait for the try of all the other miners before starting hashing again.  

## Output

Below is an output example from the execution of the *Main.java* class. As we can observe, *Miner N°4* obtained the correct hash value (starting with four 0's as *difficulty* = 4) required for the proof of work, before its three other concurrents. As a result, *Miner N°4* received a reward of 1 bitcoin.

![image](https://github.com/WatiBenj/Blockchain_Ajtai_Hash/blob/master/Output.PNG)

## Support

Benjamin Fischer - benjamin.fischer@etu.unige.ch

## Future Work

The signing process ensuring the authenticity of transactions and the integrity of blocks is left as a future work. This could be realized using a hash-based signature scheme, as XMSS, in order to achieve a fully quantum-secure blockchain implementation.


