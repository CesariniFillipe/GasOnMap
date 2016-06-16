<?php
/**
 * Created by PhpStorm.
 * User: Willian Soares
 * Date: 07/06/2016
 * Time: 23:17
 */

class DB_Functions {

    private $conn;

    function __construct() {
        require_once 'DB_Connect.php';
        $db = new Db_Connect();
        $this->conn = $db->connect();
    }

    function __destruct() {
    }

    /**
     * Storing new user
     * returns user details
     */
    public function storeUser($name, $email, $password) {

        $query = $this->conn->prepare("INSERT INTO Usuario(name, email, senha, criado_em) VALUES(?, ?, ?, NOW())");
        $query->bind_param("sss", $name, $email, $password);
        $result = $query->execute();
        $query->close();

        // check for successful store
        if ($result) {
            $query = $this->conn->prepare("SELECT * FROM users WHERE email = ?");
            $query->bind_param("s", $email);
            $query->execute();
            $user = $query->get_result()->fetch_assoc();
            $query->close();

            return $user;
        } else {
            return false;
        }
    }

    /**
     * Get user by email and password
     */
    public function getUserByEmailAndPassword($email, $password) {

        $query = $this->conn->prepare("SELECT * FROM Usuario WHERE email = ?");

        $query->bind_param("s", $email);

        if ($query->execute()) {
            $user = $query->get_result()->fetch_assoc();
            $query->close();

            // verifying user password
            if($password == $user['senha']) return $user;
        }
        else
            return NULL;
    }

    /**
     * Check user is existed or not
     */
    public function isUserExisted($email) {
        $query = $this->conn->prepare("SELECT email from Usuario WHERE email = ?");

        $query->bind_param("s", $email);

        $query->execute();

        $query->store_result();

        if ($query->num_rows > 0) {
            // user existed
            $query->close();
            return true;
        } else {
            // user not existed
            $query->close();
            return false;
        }
    }
}

?>