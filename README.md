# Blockchain - Ajtai Hash Function

Blockchain Simulation using Lattice-Based Ajtai Hash Function

## Introduction

In this project, which is part of a Bachelor's thesis in Computer Science on the theme of *post-quantum cryptography*,
it is proposed to use a lattice-based hash function, better known as the *Ajtai hash function*,
instead of the standard SHA-256, in the implementation of a basic blockchain.

## Description

This blockchain simulation is composed of the following 5 Java classes :

- **Transaction.java**

   A transaction can simply be expressed as the set of the next fields :
  
   - Identifier
   - Name of the sender
   - Name of the receiver
   - Amount
   
- **Block.java**

  The necessary fields for the complete representation of a block include the following elements :
  
   - Hash of the previous block
   - Merkle root (computed from a set of transactions)
   - Nonce
   - Hash of the block (AjtaiHash(Previous Hash + Merkle Root + Nonce))
   - Timestamp (date + time)
   - Name of the miner

- **Miner.java**

  To fully represent a miner, the following attributes are required :
  
   - Name of the miner
   - Own copy of the Ajtai key
   - Own copy of the blockchain
   - Reward (amount of Bitcoins)
 
  This class is also equipped with two essential methods concerning the mining of a block (*mineBlock*) and the verification of the validity of the blockchain (*checkChainValidity*). A single call to *mineBlock* generates a single hash value that counts as an attempt to satisfy the proof of work. The validity of the blockchain is checked by each miner after adding a new mined block. This verification procedure includes the three next points :
  
   - Comparison of the hash of the previous block and the previous hash of the current block
   - Comparison of the registered hash of the current block and the newly computed hash of the current block
   - Verification of the proof of work (check if the hash of the current block starts with the correct number of 0's specified by the parameter *difficulty*)

- **Utility.java**

  This class is only used as a helper containing the definitions of two calculation methods that are essential in the mining process, namely the Merkle root computation (*computeMerkleRoot*) and the Ajtai hash function computation (*AjtaiHash*). It was only created to avoid duplicating the code of these methods in the Block and Miner classes.
  
  The Merkle root is computed in the usual manner using the transaction's identifiers as leaves.
  
  The Ajtai hash function is parametrized by the three following integers which define an (*n* x *m*) random binary matrix, called the Ajtai key.
  
   - *n* : length of the output hash value (hexadecimal representation)
   - *m* : maximal length of the input string (binary representation)
   - *q* : modulus

 - **Environment.java** (Main class)
 
   This class includes all the stages required to add a new block to the blockchain. More precisely, this procedure can be described by the following steps :
  
  1. Definition of the Ajtai key (random binary matrix)
  2. Instanciation of the first block (genesis)
  3. Addition of the genesis block to the (empty) blockchain
  4. Instanciation of 4 miners with their own copy of the Ajtai key and the initial blockchain
  5. Instanciation of 8 transactions
  6. Computation of the Merkle root from the set of transactions by each miner
  7. Mining process :
     - Each miner attempts succesively to mine the next block by applying the Ajtai hash function on the concatenation of the hash of the previous bloc, the Merkle root and the nonce
     - This step is repeated many times by randomly varying the nonce until the proof of work is verified (hash must begin with a certain number of 0)
     - The process ends immediately as soon as one of the miners obtains the desired hash value
  8. Addition of the new mined block to the blockchain of each miner
  9. Verification of the blockchain's validity by each miner
  10. Attribution of 1 Bitcoin to the miner who mined the new block


## Output

Below is an example output of the project's execution (*Environment.java*). As we can observe, Miner N°4 has demonstrated a proof of work before its three other concurrents and has thus won 1 bitcoin as a reward.

![alt text](https://github.com/WatiBenj/Blockchain_Ajtai_Hash/blob/master/Output.PNG)

## Support

Benjamin Fischer - benjamin.fischer@etu.unige.ch

## Future Work

Implementation of the signing process to guarantee the authenticity of transactions and the integrity of blocks,
using a hash-based signature scheme (XMSS) to achieve a fully quantum-secure blockchain simulation.


