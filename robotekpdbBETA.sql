-- SECCION DE CREACION DE TABLAS

CREATE TABLE students (
	noControl VARCHAR(10) NOT NULL,
	studentName VARCHAR(70) NOT NULL,
	age INT NOT NULL,
	bloodType VARCHAR(3) NOT NULL,
	gradeLevel VARCHAR(50) NOT NULL,
	allergies BOOL NOT NULL,
	allergyDescription VARCHAR(400),
	tutorName VARCHAR(70) NOT NULL,
	tutorPhoneNumber VARCHAR(15) NOT NULL,
	email VARCHAR(100) NOT NULL,
	emergencyContact VARCHAR(70),
	emergencyPhoneNumber VARCHAR(15),
	PRIMARY KEY (noControl)
);

CREATE TABLE address (
   idAddres INT NOT NULL AUTO_INCREMENT,
   street VARCHAR(100) NOT NULL,
   exteriorNumber INT NOT NULL,
   interiorNumber INT,
   neighborhood VARCHAR(100) NOT NULL,
   city VARCHAR(100) NOT NULL,
   noControl VARCHAR(10) NOT NULL,
   PRIMARY KEY (idAddres),
   FOREIGN KEY (noControl) REFERENCES students (noControl) ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE courses (
	idLevelName VARCHAR(40) NOT NULL,
	levelNumber INT NOT NULL,
	levelDescription VARCHAR(400) NOT NULL,
	capacity INT NOT NULL,
	PRIMARY KEY (idlevelName)
);

CREATE TABLE enrollments (
	idEnrollment VARCHAR(10) NOT NULL,
	noControl VARCHAR(10) NOT NULL,
	idlevelName VARCHAR(40) NOT NULL,
	startDate DATE NOT NULL,
	monthlyAmount DECIMAL(10,2) NOT NULL,
	monthsQuantity INT NOT NULL,
	paidAmount DECIMAL(10,2) NOT NULL,
	debt DECIMAL(10,2) NOT NULL,
	status ENUM('active', 'inactive') NOT NULL DEFAULT 'active',
	fechaBaja DATE,
	PRIMARY KEY (idEnrollment),
	FOREIGN KEY (noControl) REFERENCES students(noControl) ON UPDATE CASCADE ON DELETE RESTRICT,
	FOREIGN KEY (idlevelName) REFERENCES courses(idlevelName) ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE payments (
  idPaymentNumber INT NOT NULL,
  idEnrollment VARCHAR(10) NOT NULL,
  recentPayment DECIMAL(10,2) NOT NULL,
  paymentDate DATE NOT NULL,
  paymentMonth DATE NOT NULL,
  PRIMARY KEY (idPaymentNumber),
  FOREIGN KEY (idEnrollment) REFERENCES enrollments(idEnrollment) ON UPDATE CASCADE ON DELETE RESTRICT
);

-- AGREGAR ESTUDIANTE (PROCEDIMIENTO)
DELIMITER //

CREATE PROCEDURE addStudent(
   IN p_noControl VARCHAR(10),
   IN p_studentName VARCHAR(70),
   IN p_age INT,
   IN p_bloodType VARCHAR(3),
   IN p_gradeLevel VARCHAR(50),
   IN p_allergies BOOL,
   IN p_allergyDescription VARCHAR(400),
   IN p_tutorName VARCHAR(70),
   IN p_tutorPhoneNumber VARCHAR(15),
   IN p_email VARCHAR(100),
   IN p_emergencyContact VARCHAR(70),
   IN p_emergencyPhoneNumber VARCHAR(15),
   IN p_street VARCHAR(100),
   IN p_exteriorNumber VARCHAR(5),
   IN p_interiorNumber VARCHAR(5),
   IN p_neighborhood VARCHAR(100),
	IN p_city VARCHAR(100)
)
BEGIN
   INSERT INTO students(
      noControl, 
      studentName, 
      age, 
      bloodType, 
      gradeLevel, 
      allergies, 
      allergyDescription, 
      tutorName, 
      tutorPhoneNumber, 
      email, 
      emergencyContact, 
      emergencyPhoneNumber
   )VALUES(
      p_noControl,
      p_studentName,
      p_age,
      p_bloodType,
      p_gradeLevel,
      p_allergies,
      p_allergyDescription,
      p_tutorName,
      p_tutorPhoneNumber,
      p_email,
      p_emergencyContact,
      p_emergencyPhoneNumber
   );
    
	INSERT INTO address(
   	street,
      exteriorNumber,
      interiorNumber,
      neighborhood,
      city,
      noControl
   )VALUES(
      p_street,
      p_exteriorNumber,
      p_interiorNumber,
      p_neighborhood,
      p_city,
      p_noControl
   );
END //

DELIMITER;

---- EDITAR ESTUDIANTE (PROCEDIMIENTO)

DELIMITER //

CREATE PROCEDURE editStudent(
    IN p_oldNoControl VARCHAR(10),
    IN p_newNoControl VARCHAR(10),
    IN p_studentName VARCHAR(70),
    IN p_age INT,
    IN p_bloodType VARCHAR(3),
    IN p_gradeLevel VARCHAR(50),
    IN p_allergies BOOL,
    IN p_allergyDescription VARCHAR(400),
    IN p_tutorName VARCHAR(70),
    IN p_tutorPhoneNumber VARCHAR(15),
    IN p_email VARCHAR(100),
    IN p_emergencyContact VARCHAR(70),
    IN p_emergencyPhoneNumber VARCHAR(15),
    IN p_street VARCHAR(100),
    IN p_exteriorNumber VARCHAR(5),
    IN p_interiorNumber VARCHAR(5),
    IN p_neighborhood VARCHAR(100),
    IN p_city VARCHAR(100)
)
BEGIN
   DECLARE oldNoControl VARCHAR(10);
   SELECT noControl INTO oldNoControl FROM students WHERE noControl = p_oldNoControl;
   IF oldNoControl IS NOT NULL THEN
      UPDATE students SET
         noControl = p_newNoControl,
         studentName = p_studentName,
         age = p_age,
         bloodType = p_bloodType,
         gradeLevel = p_gradeLevel,
         allergies = p_allergies,
         allergyDescription = p_allergyDescription,
         tutorName = p_tutorName,
         tutorPhoneNumber = p_tutorPhoneNumber,
         email = p_email,
         emergencyContact = p_emergencyContact,
         emergencyPhoneNumber = p_emergencyPhoneNumber
      WHERE noControl = oldNoControl;
        
      UPDATE address SET
         street = p_street,
         exteriorNumber = p_exteriorNumber,
         interiorNumber = p_interiorNumber,
         neighborhood = p_neighborhood,
         city = p_city
      WHERE noControl = oldNoControl;
        
      SELECT CONCAT('El estudiante con número de control ', p_oldNoControl, ' ha sido editado exitosamente.') AS result;
   ELSE
      SELECT CONCAT('El número de control ', p_oldNoControl, ' no existe en la base de datos.') AS result;
   END IF;
END //

DELIMITER ;

-- AGREGAR CURSO (PROCEDIMIENTO)

DELIMITER //

CREATE PROCEDURE addCourse(
	IN p_idLevelName VARCHAR(40),
   IN p_levelNumber INT,
   IN p_levelDescription VARCHAR(400),
   IN p_capacity INT
)
BEGIN
   INSERT INTO courses(
   	idLevelName,
      levelNumber,  
      levelDescription, 
      capacity
   )
   VALUES(
   	p_idLevelName,
      p_levelNumber,
      p_levelDescription,
      p_capacity
   );
END //

DELIMITER ;

-- EDITAR CURSO (PROCEDIMIENTO)

DELIMITER //

CREATE PROCEDURE editCourse(
   IN p_oldIdLevelName VARCHAR(40),
   IN p_newIdLevelName VARCHAR(40),
   IN p_levelNumber INT,
   IN p_levelDescription VARCHAR(400),
   IN p_capacity INT
)
BEGIN
   DECLARE v_idLevelName VARCHAR(40);
   SELECT idLevelName INTO v_idLevelName FROM courses WHERE idLevelName = p_oldIdLevelName;
   
   IF v_idLevelName IS NULL THEN
      SIGNAL SQLSTATE '45000'
         SET MESSAGE_TEXT = 'Error: the course does not exist';
   ELSE
      UPDATE courses SET
      	idLevelName = p_newIdLevelName,
         levelNumber = p_levelNumber,
         levelDescription = p_levelDescription,
         capacity = p_capacity
      WHERE idLevelName = v_idLevelName;
   END IF;
END //

DELIMITER ;

-- PROCEDIMIENTO PARA COMPROBAR SI HAY YA UN ALUMNO REGISTRADO CON ALGUN CURSO

DELIMITER //
CREATE FUNCTION alreadyEnrolled(no_control VARCHAR(10), id_levelname VARCHAR(40))
RETURNS BOOLEAN
BEGIN
  DECLARE result BOOLEAN;
  SELECT EXISTS(SELECT * FROM enrollments WHERE noControl = no_control AND idlevelName = id_levelname) INTO result;
  RETURN result;
END//
DELIMITER ;

-- AGREGAR INSCRIPCION (PROCEDIMIENTO)

DELIMITER //

CREATE PROCEDURE addEnrollment(
	IN p_idEnrollment VARCHAR(10),
	IN p_no_control VARCHAR(10),
	IN p_id_levelname VARCHAR(40),
	IN p_start_date DATE,
	IN p_monthly_amount DECIMAL(10,2),
	IN p_months_quantity INT
)
BEGIN
  IF alreadyEnrolled(p_no_control, p_id_levelname) THEN
    SELECT 'Error: El estudiante ya está inscrito en este curso.' AS message;
  ELSE
    INSERT INTO enrollments (
      idEnrollment,
      noControl,
      idlevelName,
      startDate,
      monthlyAmount,
      monthsQuantity,
      paidAmount,
      debt,
      status,
      fechaBaja
    ) VALUES (
      p_idEnrollment,
      p_no_control,
      p_id_levelname,
      p_start_date,
      p_monthly_amount,
      p_months_quantity,
      0,
      p_monthly_amount * p_months_quantity,
      'active',
      null
    );
    SELECT CONCAT('Se ha agregado una nueva inscripción con ID ', LAST_INSERT_ID()) AS message;
  END IF;
END//
DELIMITER ;


-- EDITAR INSCRIPCION (PROCEDIMIENTO)

DELIMITER //

CREATE PROCEDURE editEnrollment(
	IN p_oldIdEnrollment VARCHAR(10),
	IN p_newIdEnrollment VARCHAR(10),
	IN p_no_control VARCHAR(10),
	IN p_id_levelname VARCHAR(40),
	IN p_start_date DATE,
	IN p_monthly_amount DECIMAL(10,2),
	IN p_months_quantity INT
)
BEGIN

	DECLARE v_idEnrollment VARCHAR(40);
   SELECT idEnrollment INTO v_idEnrollment FROM enrollments WHERE idEnrollment = p_oldIdEnrollment;
   
   IF v_idEnrollment IS NULL THEN
      SIGNAL SQLSTATE '45000'
         SET MESSAGE_TEXT = 'Error: the enrollment does not exist';
   ELSE
		IF alreadyEnrolled(p_no_control, p_id_levelname) THEN
	    	SELECT 'Error: El estudiante ya está inscrito en este curso.' AS message;
	  	ELSE
	    	UPDATE enrollments SET 
	    		
				idEnrollment =  p_newIdEnrollment,
	    		noControl = p_no_control,
      		idlevelName = p_id_levelname,
	    		startDate = p_start_date,
	    		monthlyAmount = p_monthly_amount,
	    		monthsQuantity = p_months_quantity,
	    		debt = p_monthly_amount * p_months_quantity
	    		
			WHERE idEnrollment = v_idEnrollment;
				
	    	SELECT CONCAT('Se ha editado una inscripción') AS message;
	  	END IF;
	END IF;
END//
DELIMITER ;

-- SUMAR Y ASIGNAR LA CANTIDAD A SU RESPECTIVA INSCRIPCION

DELIMITER //

CREATE PROCEDURE calculatePaidDebt(
	IN p_idEnrollment VARCHAR(10)
)
BEGIN

	DECLARE v_paidAmount DECIMAL(10,2);
	DECLARE v_monthsQuantity INT;
	DECLARE v_monthlyAmount DECIMAL(10,2);
	DECLARE v_debt DECIMAL(10,2);
	
	SELECT SUM(recentPayment) INTO v_paidAmount FROM payments WHERE idEnrollment = p_idEnrollment;
	
	IF v_paidAmount IS NULL THEN
		SET v_paidAmount = 0;
	END IF;
	
	
	SELECT monthlyAmount INTO v_monthlyAmount FROM enrollments WHERE idEnrollment = p_idEnrollment;
	SELECT monthsQuantity INTO v_monthsQuantity FROM enrollments WHERE idEnrollment = p_idEnrollment;
	
	IF v_monthsQuantity IS NULL THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'Error: the enrollment does not exist';
	ELSE
		SET v_debt = (v_monthlyAmount * v_monthsQuantity) - v_paidAmount;
		UPDATE enrollments SET debt = v_debt, paidAmount = v_paidAmount WHERE idEnrollment = p_idEnrollment;
	END IF;
	
END//

DELIMITER ;

-- AFREGAR PAGO PROCEDIMIENTO

DELIMITER //

CREATE PROCEDURE addPayment(
  IN p_idPaymentNumber INT,
  IN p_idEnrollment VARCHAR(10),
  IN p_recentPayment DECIMAL(10,2),
  IN p_paymentDate DATE,
  IN p_paymentMonth DATE
)
BEGIN
  INSERT INTO payments (
    idPaymentNumber,
    idEnrollment,
    recentPayment,
    paymentDate,
    paymentMonth
  ) VALUES (
    p_idPaymentNumber,
    p_idEnrollment,
    p_recentPayment,
    p_paymentDate,
    p_paymentMonth
  );

  CALL calculatePaidDebt(p_idEnrollment);
END //

DELIMITER ;

-- EDITAR PAGO (PROCEDIMIENTO)

DELIMITER //

CREATE PROCEDURE editPayment(
    IN p_oldIdPaymentNumber INT,
    IN p_newIdPaymentNumber INT,
    IN p_oldIdEnrollment VARCHAR(10),
    IN p_newIdEnrollment VARCHAR(10),
    IN p_recentPayment DECIMAL(10,2),
    IN p_paymentDate DATE,
    IN p_paymentMonth DATE
)
BEGIN
    DECLARE v_idPaymentNumber INT;
    DECLARE v_idEnrollment VARCHAR(10);
    
    SELECT idPaymentNumber INTO v_idPaymentNumber FROM payments WHERE idPaymentNumber = p_oldIdPaymentNumber;
   SELECT idEnrollment INTO v_idEnrollment FROM enrollments WHERE idEnrollment = p_oldIdEnrollment;
   
	 IF v_idPaymentNumber IS NULL or v_idEnrollment IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error: the payment or enrollment does not exist';
    ELSE
        UPDATE payments SET
            idPaymentNumber = p_newIdPaymentNumber,
            idEnrollment = p_newIdEnrollment,
            recentPayment = p_recentPayment,
            paymentDate = p_paymentDate,
            paymentMonth = p_paymentMonth
        WHERE idPaymentNumber = v_idPaymentNumber;

        CALL calculatePaidDebt(p_oldIdEnrollment);
        CALL calculatePaidDebt(p_newIdEnrollment);

        SELECT CONCAT('Se ha editado un pago') AS message;
    END IF;
END //

DELIMITER ;
