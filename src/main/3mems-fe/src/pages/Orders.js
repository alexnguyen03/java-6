import {faBoxOpen, faCartArrowDown, faChartPie, faChevronDown, faClipboard, faCommentDots, faFileAlt, faPlus, faRocket, faStore} from '@fortawesome/free-solid-svg-icons';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {Button, Col, Dropdown, Row} from '@themesberg/react-bootstrap';
import React from 'react';
import {GeneralInfoForm} from '../components/Forms';

import DataTable from 'react-data-table-component';

export default () => {
	const columns = [
		{
			name: 'Title',
			selector: (row) => row.title,
			sortable: true,
		},
		{
			name: 'Year',
			selector: (row) => row.year,
			sortable: true,
		},
	];

	const data = [
		{
			id: 1,
			title: 'Beetlejuice',
			year: '1988',
		},
		{
			id: 2,
			title: 'Ghostbusters',
			year: '1984',
		},
		{
			id: 3,
			title: 'Ghostbusters',
			year: '1984',
		},
		{
			id: 4,
			title: 'Ghostbusters',
			year: '1984',
		},
		{
			id: 5,
			title: 'Ghostbusters',
			year: '1984',
		},
		{
			id: 6,
			title: 'Ghostbusters',
			year: '1984',
		},
		{
			id: 7,
			title: 'Ghostbusters',
			year: '1984',
		},
		{
			id: 8,
			title: 'Ghostbusters',
			year: '1984',
		},
		{
			id: 9,
			title: 'Ghostbusters',
			year: '1984',
		},
		{
			id: 10,
			title: 'Ghostbusters',
			year: '1984',
		},
		{
			id: 11,
			title: 'Ghostbusters',
			year: '1984',
		},
		{
			id: 12,
			title: 'Ghostbusters',
			year: '1984',
		},
	];

	const handleRowClicked = (row) => {
		console.log(row.title);
	};

	return (
		<>
			<div className='d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center py-4'>
				<Dropdown>
					<Dropdown.Toggle
						as={Button}
						variant='secondary'
						className='text-dark me-2'>
						<FontAwesomeIcon
							icon={faPlus}
							className='me-2'
						/>
						<span>orders</span>
					</Dropdown.Toggle>
					<Dropdown.Menu className='dashboard-dropdown dropdown-menu-left mt-2'>
						<Dropdown.Item>
							<FontAwesomeIcon
								icon={faFileAlt}
								className='me-2'
							/>{' '}
							Document
						</Dropdown.Item>
						<Dropdown.Item>
							<FontAwesomeIcon
								icon={faCommentDots}
								className='me-2'
							/>{' '}
							Message
						</Dropdown.Item>
						<Dropdown.Item>
							<FontAwesomeIcon
								icon={faBoxOpen}
								className='me-2'
							/>{' '}
							Product
						</Dropdown.Item>

						<Dropdown.Divider />

						<Dropdown.Item>
							<FontAwesomeIcon
								icon={faRocket}
								className='text-danger me-2'
							/>{' '}
							Subscription Plan
						</Dropdown.Item>
					</Dropdown.Menu>
				</Dropdown>

				<div className='d-flex'>
					<Dropdown>
						<Dropdown.Toggle
							as={Button}
							variant='primary'>
							<FontAwesomeIcon
								icon={faClipboard}
								className='me-2'
							/>{' '}
							Reports
							<span className='icon icon-small ms-1'>
								<FontAwesomeIcon icon={faChevronDown} />
							</span>
						</Dropdown.Toggle>
						<Dropdown.Menu className='dashboard-dropdown dropdown-menu-left mt-1'>
							<Dropdown.Item>
								<FontAwesomeIcon
									icon={faBoxOpen}
									className='me-2'
								/>{' '}
								Products
							</Dropdown.Item>
							<Dropdown.Item>
								<FontAwesomeIcon
									icon={faStore}
									className='me-2'
								/>{' '}
								Customers
							</Dropdown.Item>
							<Dropdown.Item>
								<FontAwesomeIcon
									icon={faCartArrowDown}
									className='me-2'
								/>{' '}
								Orders
							</Dropdown.Item>
							<Dropdown.Item>
								<FontAwesomeIcon
									icon={faChartPie}
									className='me-2'
								/>{' '}
								Console
							</Dropdown.Item>

							<Dropdown.Divider />

							<Dropdown.Item>
								<FontAwesomeIcon
									icon={faRocket}
									className='text-success me-2'
								/>{' '}
								All Reports
							</Dropdown.Item>
						</Dropdown.Menu>
					</Dropdown>
				</div>
			</div>

			<Row>
				<Col
					xs={12}
					xl={12}>
					<GeneralInfoForm />
				</Col>
			</Row>
			<Row>
				<Col
					xs={12}
					xl={12}>
					<DataTable
						columns={columns}
						data={data}
						pagination
						highlightOnHover
						onRowDoubleClicked={handleRowClicked}
					/>
				</Col>
			</Row>
		</>
	);
};
