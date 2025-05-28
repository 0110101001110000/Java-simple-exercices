
package br.unit.entities;


// Init -------------------------------------------------------------------- //


/**
 * Essa classe representa um registro de um aluno, armazenando: matrícula, data de matrícula, nome do aluno, data de nascimento e curso.
 * @author 01101010-01110000
 * @param enrolment Matrícula do aluno
 * @param enrollmentDate Data de matrícula
 * @param name Nome do aluno
 * @param birthDate Data de nascimento
 * @param course Curso do aluno
 * @since 1.0
 */
public record StudentEntity(Long enrolment, String enrollmentDate, String name, String birthDate, String course) {}
